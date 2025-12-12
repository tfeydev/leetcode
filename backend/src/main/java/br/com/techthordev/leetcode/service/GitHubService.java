package br.com.techthordev.leetcode.service;

import br.com.techthordev.leetcode.config.GithubProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class GitHubService {

    private final JdbcTemplate jdbc;
    private final GithubProperties props;
    private final RestTemplate rest = new RestTemplate();

    public GitHubService(JdbcTemplate jdbc, GithubProperties props) {
        this.jdbc = jdbc;
        this.props = props;
    }

    /**
     * Fetch all SQL files from the GitHub repository and insert/update them in the DB.
     * @return number of imported files
     */
    public int importSqlFilesToDb() {
        String apiUrl = props.getTreeApiUrl();
        ResponseEntity<Map> resp = rest.getForEntity(apiUrl, Map.class);

        if (!resp.getStatusCode().is2xxSuccessful() || resp.getBody() == null) {
            return 0;
        }

        Object treeObj = resp.getBody().get("tree");
        if (!(treeObj instanceof List)) return 0;

        List<?> tree = (List<?>) treeObj;
        int count = 0;

        for (Object itemObj : tree) {
            if (!(itemObj instanceof Map)) continue;
            Map item = (Map) itemObj;

            Object pathObj = item.get("path");
            Object typeObj = item.get("type");

            if (pathObj instanceof String && "blob".equals(typeObj)) {
                String path = (String) pathObj;
                if (path.toLowerCase().endsWith(".sql")) {
                    String url = props.getRawBaseUrl() + path;
                    try {
                        ResponseEntity<String> fileResp = rest.getForEntity(url, String.class);
                        if (fileResp.getStatusCode().is2xxSuccessful() && fileResp.getBody() != null) {
                            String sqlCode = fileResp.getBody();

                            // Insert or update in problems table
                            jdbc.update(
                                    "INSERT INTO leetcode.problems (difficulty, problem, path, sql_code) " +
                                            "VALUES (?, ?, ?, ?) " +
                                            "ON CONFLICT (path) DO UPDATE SET sql_code = EXCLUDED.sql_code",
                                    extractDifficulty(path),
                                    extractProblemName(path),
                                    path,
                                    sqlCode
                            );
                            count++;
                        }
                    } catch (Exception e) {
                        System.err.println("Failed to fetch SQL file: " + path + " -> " + e.getMessage());
                    }
                }
            }
        }
        return count;
    }

    /**
     * Extract difficulty from file path.
     * Example: "easy/175_combine_two_tables/solution.sql" -> "easy"
     */
    private String extractDifficulty(String path) {
        String[] parts = path.split("/");
        return parts.length > 0 ? parts[0] : "unknown";
    }

    /**
     * Extract problem name from file path.
     * Example: "easy/175_combine_two_tables/solution.sql" -> "175_combine_two_tables"
     */
    private String extractProblemName(String path) {
        String[] parts = path.split("/");
        return parts.length > 1 ? parts[1] : path;
    }
}
