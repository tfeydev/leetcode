package br.com.techthordev.leetcode.controller;

import br.com.techthordev.leetcode.service.DbProblemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/problems")
public class DbProblemsController {

    private final DbProblemsService dbProblemsService;

    public DbProblemsController(DbProblemsService dbProblemsService) {
        this.dbProblemsService = dbProblemsService;
    }

    /**
     * Returns a list of all imported problems from the database.
     */
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listProblems() {
        return ResponseEntity.ok(dbProblemsService.listProblems());
    }

    /**
     * Returns the raw SQL code for a given problem path.
     */
    @GetMapping("/sql")
    public ResponseEntity<String> getSqlCode(@RequestParam("path") String path) {
        String sql = dbProblemsService.getSqlCode(path);
        return sql == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(sql);
    }

    /**
     * Returns the last stored execution result for a given problem path.
     */
    @GetMapping("/results")
    public ResponseEntity<String> getResult(@RequestParam("path") String path) {
        String json = dbProblemsService.getResult(path);
        return json == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(json);
    }

    /**
     * Executes the SQL code for a given problem and stores the result in the database.
     */
    @PostMapping("/execute")
    public ResponseEntity<List<Map<String, Object>>> execute(@RequestParam("path") String path) {
        try {
            List<Map<String, Object>> result = dbProblemsService.executeAndStore(path);
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(List.of(Map.of("error", e.getMessage())));
        }
    }
}
