package br.com.techthordev.leetcode.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DbProblemsService {

    private final JdbcTemplate jdbc;
    private final ObjectMapper mapper = new ObjectMapper();

    public DbProblemsService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Returns all problems stored in the database.
     */
    public List<Map<String, Object>> listProblems() {
        return jdbc.queryForList(
                "SELECT difficulty, problem, path FROM leetcode.problems ORDER BY difficulty, problem"
        );
    }

    /**
     * Returns the SQL code for a specific problem path.
     */
    public String getSqlCode(String path) {
        return jdbc.query(
                "SELECT sql_code FROM leetcode.problems WHERE path = ?",
                rs -> rs.next() ? rs.getString(1) : null,
                path
        );
    }

    /**
     * Returns the last stored result for a specific problem path.
     */
    public String getResult(String path) {
        return jdbc.query(
                "SELECT result_json FROM leetcode.execution_results WHERE problem_path = ?",
                rs -> rs.next() ? rs.getString(1) : null,
                path
        );

    }

    /**
     * Executes the SQL code for a problem and stores the result in the database.
     */
    public List<Map<String, Object>> executeAndStore(String path) {
        String sql = getSqlCode(path);
        if (sql == null) throw new RuntimeException("No SQL code found for path: " + path);

        List<Map<String, Object>> result;
        try {
            result = jdbc.queryForList(sql);
        } catch (Exception e) {
            throw new RuntimeException("SQL execution failed: " + e.getMessage(), e);
        }

        try {
            String json = mapper.writeValueAsString(result);
            jdbc.update(
                    "INSERT INTO leetcode.execution_results (problem_path, result_json, executed_at) " +
                            "VALUES (?, ?::jsonb, now()) " +
                            "ON CONFLICT (problem_path) DO UPDATE " +
                            "SET result_json = EXCLUDED.result_json, executed_at = now()",
                    path, json
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to store execution result: " + e.getMessage(), e);
        }

        return result;
    }
}
