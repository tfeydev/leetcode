package br.com.techthordev.leetcode.runner;

import br.com.techthordev.leetcode.service.GitHubService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * LocalImportRunner automatically imports SQL files from GitHub
 * into the database when the application starts.
 *
 * This component is only active when the "local" Spring profile is enabled.
 * In production, the import should be triggered via CI/CD or a scheduler.
 */
@Component
@Profile("local")
public class LocalImportRunner {

    private final GitHubService gitHubService;

    public LocalImportRunner(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    /**
     * Executes once after the application context is initialized.
     * Calls the GitHubService to import SQL files into the DB.
     */
    @PostConstruct
    public void runImport() {
        int count = gitHubService.importSqlFilesToDb();
        System.out.println("LocalImportRunner: Imported " + count + " SQL files into DB at startup.");
    }
}
