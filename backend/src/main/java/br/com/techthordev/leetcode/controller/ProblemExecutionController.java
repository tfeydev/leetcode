package br.com.techthordev.leetcode.controller;

import br.com.techthordev.leetcode.service.SqlExecutionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/problems")
public class ProblemExecutionController {

    private final SqlExecutionService sqlExecutionService;

    public ProblemExecutionController(SqlExecutionService sqlExecutionService) {
        this.sqlExecutionService = sqlExecutionService;
    }

    /**
     * Executes the SQL solution for a specific problem via Query Parameter.
     * URL Example: <a href="http://localhost:8080/api/problems/execute?path=easy/175_combine_two_tables/solution">...</a>
     */
    @GetMapping("/execute")
    public ResponseEntity<List<Map<String, Object>>> executeProblem(
            @RequestParam("path") String path) {

        try {
            List<Map<String, Object>> result = sqlExecutionService.executeProblemSql(path);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            System.err.println("Execution Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    List.of(Map.of("error", e.getMessage()))
            );
        }
    }
}