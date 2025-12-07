package br.com.techthordev.leetcode.service;

import br.com.techthordev.leetcode.config.GithubProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GithubRepoService {

    private static final Logger logger = LoggerFactory.getLogger(GithubRepoService.class);

    private final GithubProperties props;
    private final RestTemplate restTemplate;
    private static final String SQL_EXTENSION = ".sql";
    private static final String SQL_PATH_PREFIX = "postgresql/";

    public GithubRepoService(GithubProperties props, RestTemplate restTemplate) {
        this.props = props;
        this.restTemplate = restTemplate;
    }

    public List<String> listAllSqlFiles() {
        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    props.getTreeApiUrl(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, Object>>() {}
            );

            if (response.getBody() == null) return Collections.emptyList();

            Object treeObj = response.getBody().get("tree");
            if (!(treeObj instanceof List)) return Collections.emptyList();

            List<?> rawList = (List<?>) treeObj;
            return rawList.stream()
                    .filter(item -> item instanceof Map)
                    .map(item -> (Map<?, ?>) item)
                    .filter(m -> "blob".equals(String.valueOf(m.get("type"))))
                    .map(m -> String.valueOf(m.get("path")))
                    .filter(path -> path.startsWith(SQL_PATH_PREFIX) && path.endsWith(SQL_EXTENSION))
                    .map(path -> path.substring(SQL_PATH_PREFIX.length(), path.length() - SQL_EXTENSION.length()))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Error listing files from GitHub API: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public String readSqlFileContent(String relativePath) {
        // Normalize rawBaseUrl to end with '/'
        String base = props.getRawBaseUrl();
        if (!base.endsWith("/")) base = base + "/";

        // Build safe URL: append SQL_PATH_PREFIX + encoded segments + .sql
        String encoded = URLEncoder.encode(relativePath, StandardCharsets.UTF_8)
                // URLEncoder encodes slashes; restore them for path segments
                .replace("%2F", "/");
        String fullUrl = base + SQL_PATH_PREFIX + encoded + SQL_EXTENSION;

        logger.info("Fetching SQL from: {}", fullUrl);

        try {
            return restTemplate.getForObject(fullUrl, String.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new RuntimeException("SQL file not found on GitHub: " + fullUrl, e);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching SQL file from GitHub: " + e.getMessage(), e);
        }
    }
}
