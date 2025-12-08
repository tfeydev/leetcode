package br.com.techthordev.leetcode.controller;

import br.com.techthordev.leetcode.service.GithubRepoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SqlFileController {

    private final GithubRepoService githubRepoService;

    public SqlFileController(GithubRepoService githubRepoService) {
        this.githubRepoService = githubRepoService;
    }

    @GetMapping("/sql-files")
    public ResponseEntity<List<String>> getSqlFiles() {
        List<String> paths = githubRepoService.listAllSqlFiles();
        return ResponseEntity.ok(paths);
    }

    /**
     * Query param version:
     * Example: curl "<a href="http://localhost:8080/api/sql-content?path=easy/175_combine_two_tables/solution">...</a>"
     */
    @GetMapping(value = "/sql-content", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getSqlContent(@RequestParam("path") String path) {
        try {
            String content = githubRepoService.readSqlFileContent(path);
            if (content == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("SQL not found for path: " + HtmlUtils.htmlEscape(path));
            }
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(content);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Could not fetch SQL: " + HtmlUtils.htmlEscape(e.getMessage()));
        }
    }

    /**
     * Path-based version:
     * Example: <a href="http://localhost:8080/api/sql-content/easy/175_combine_two_tables/solution">...</a>
     */
    @GetMapping(value = "/sql-content/**", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getSqlContentByPath(HttpServletRequest request) {
        String context = request.getContextPath() == null ? "" : request.getContextPath();
        String prefix = context + "/api/sql-content/";
        String uri = request.getRequestURI() == null ? "" : request.getRequestURI();
        String path = uri.startsWith(prefix) ? uri.substring(prefix.length()) : uri;
        path = path.replaceAll("^/+", "").replaceAll("/+$", "");

        if (path.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing path");
        }

        try {
            String content = githubRepoService.readSqlFileContent(path);
            if (content == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("SQL not found for path: " + HtmlUtils.htmlEscape(path));
            }
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(content);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Could not fetch SQL: " + HtmlUtils.htmlEscape(e.getMessage()));
        }
    }
}
