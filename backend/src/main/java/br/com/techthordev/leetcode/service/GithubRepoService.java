package br.com.techthordev.leetcode.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GithubRepoService {

    private static final String REPO_OWNER = "techthordev";
    private static final String REPO_NAME = "leetcode";
    private static final String BRANCH = "main";
    private static final String GITHUB_TREE_API =
            "https://api.github.com/repos/" + REPO_OWNER + "/" + REPO_NAME + "/git/trees/" + BRANCH + "?recursive=1";
    private static final String RAW_BASE =
            "https://raw.githubusercontent.com/" + REPO_OWNER + "/" + REPO_NAME + "/" + BRANCH + "/";

    private final RestTemplate rest = new RestTemplate();

    public List<String> listAllSqlFiles() {
        try {
            ResponseEntity<Map> resp = rest.getForEntity(GITHUB_TREE_API, Map.class);
            if (!resp.getStatusCode().is2xxSuccessful() || resp.getBody() == null) {
                return List.of();
            }
            Object treeObj = resp.getBody().get("tree");
            if (!(treeObj instanceof List)) return List.of();

            List<?> tree = (List<?>) treeObj;
            List<String> result = new ArrayList<>();
            for (Object itemObj : tree) {
                if (!(itemObj instanceof Map)) continue;
                Map item = (Map) itemObj;
                Object pathObj = item.get("path");
                Object typeObj = item.get("type");
                if (pathObj instanceof String && "blob".equals(typeObj)) {
                    String path = (String) pathObj;
                    if (path.toLowerCase().endsWith(".sql")) {
                        String withoutExt = path.substring(0, path.length() - 4);
                        result.add(withoutExt);
                    }
                }
            }
            return result;
        } catch (Exception e) {
            return List.of();
        }
    }

    public String readSqlFileContent(String path) {
        if (path == null) return null;
        String normalized = path.replaceAll("^/+", "").replaceAll("/+$", "");
        List<String> candidates = new ArrayList<>();
        candidates.add(normalized);
        if (!normalized.toLowerCase().endsWith(".sql")) {
            candidates.add(normalized + ".sql");
            candidates.add(normalized + "/solution.sql");
            candidates.add(normalized + "/test.sql");
            candidates.add(normalized + "/solution");
            candidates.add(normalized + "/test");
        }

        // try direct candidates first
        for (String candidate : candidates) {
            String url = RAW_BASE + candidate;
            try {
                ResponseEntity<String> resp = rest.getForEntity(url, String.class);
                if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null) {
                    return resp.getBody();
                }
            } catch (HttpClientErrorException.NotFound nf) {
                // try next candidate
            } catch (HttpClientErrorException e) {
                // try next candidate
            } catch (Exception e) {
                throw e;
            }
        }

        // fallback: search the repository index for a path that ends with the requested fragment
        try {
            String normalizedNoExt = normalized.replaceAll("(?i)\\.sql$", "");
            List<String> all = listAllSqlFiles();
            for (String repoPath : all) {
                if (repoPath.equals(normalizedNoExt) || repoPath.endsWith("/" + normalizedNoExt) || repoPath.endsWith(normalizedNoExt)) {
                    // try fetching repoPath + ".sql"
                    String tryUrl = RAW_BASE + repoPath + ".sql";
                    try {
                        ResponseEntity<String> resp = rest.getForEntity(tryUrl, String.class);
                        if (resp.getStatusCode().is2xxSuccessful() && resp.getBody() != null) {
                            return resp.getBody();
                        }
                    } catch (HttpClientErrorException.NotFound nf) {
                        // continue
                    }
                    // as extra attempt, try repoPath (in case list returned entries without ext but file exists without ext)
                    try {
                        ResponseEntity<String> resp2 = rest.getForEntity(RAW_BASE + repoPath, String.class);
                        if (resp2.getStatusCode().is2xxSuccessful() && resp2.getBody() != null) {
                            return resp2.getBody();
                        }
                    } catch (HttpClientErrorException.NotFound nf) {
                        // continue
                    }
                }
            }
        } catch (Exception e) {
            // ignore search failures and return null
        }

        return null;
    }
}
