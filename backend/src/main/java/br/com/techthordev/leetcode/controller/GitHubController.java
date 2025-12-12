package br.com.techthordev.leetcode.controller;

import br.com.techthordev.leetcode.service.GitHubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/github")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    /**
     * Import all SQL files from GitHub and store them in the database.
     * This endpoint is typically triggered manually or by CI/CD.
     */
    @PostMapping("/import")
    public ResponseEntity<String> importProblems() {
        int count = gitHubService.importSqlFilesToDb();
        return ResponseEntity.ok(count + " SQL files imported into DB");
    }
}
