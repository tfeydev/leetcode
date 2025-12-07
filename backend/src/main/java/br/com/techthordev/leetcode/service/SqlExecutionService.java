package br.com.techthordev.leetcode.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SqlExecutionService {

    private final JdbcTemplate jdbcTemplate;
    private final GithubRepoService githubRepoService;

    public SqlExecutionService(JdbcTemplate jdbcTemplate, GithubRepoService githubRepoService) {
        this.jdbcTemplate = jdbcTemplate;
        this.githubRepoService = githubRepoService;
    }

    private String stripComments(String sql) {
        if (sql == null) return "";
        // Remove block comments and line comments, then trim
        String noBlock = sql.replaceAll("(?s)/\\*.*?\\*/", " ");
        String noLine = noBlock.replaceAll("(?m)--.*?$", " ");
        return noLine.trim();
    }

    public List<Map<String, Object>> executeProblemSql(String problemPath) {
        String sql = githubRepoService.readSqlFileContent(problemPath);
        String cleaned = stripComments(sql);
        String firstToken = cleaned.isEmpty() ? "" : cleaned.split("\\s+")[0].toUpperCase();

        try {
            if ("SELECT".equals(firstToken)) {
                return jdbcTemplate.queryForList(sql);
            } else {
                int rowsAffected = jdbcTemplate.update(sql);
                return List.of(Map.of("rowsAffected", (Object) rowsAffected));
            }
        } catch (Exception e) {
            throw new RuntimeException("SQL Execution Failed: " + e.getMessage(), e);
        }
    }
}
