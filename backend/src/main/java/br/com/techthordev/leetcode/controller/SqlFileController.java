package br.com.techthordev.leetcode.controller;

import br.com.techthordev.leetcode.service.GithubRepoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SqlFileController {

    private final GithubRepoService githubRepoService;

    public SqlFileController(GithubRepoService githubRepoService) {
        this.githubRepoService = githubRepoService;
    }

    /**
     * Fetches a list of all available SQL problem paths.
     * URL Example: http://localhost:8080/api/sql-files
     *
     * @return A list of paths (e.g., ["easy/175_combine_two_tables/solution"]).
     */
    @GetMapping("/sql-files")
    public ResponseEntity<List<String>> getSqlFiles() {
        List<String> paths = githubRepoService.listAllSqlFiles();
        return ResponseEntity.ok(paths);
    }

    /**
     * Reads the raw content of a specific SQL file.
     * URL Example: http://localhost:8080/api/sql-content?path=easy/175_combine_two_tables/solution
     *
     * @param path The relative path to the SQL file (without the .sql extension).
     * @return The raw SQL content as a string.
     */
    @GetMapping("/sql-content")
    public ResponseEntity<String> getSqlContent(@RequestParam("path") String path) {
        try {
            String content = githubRepoService.readSqlFileContent(path);
            return ResponseEntity.ok(content);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}