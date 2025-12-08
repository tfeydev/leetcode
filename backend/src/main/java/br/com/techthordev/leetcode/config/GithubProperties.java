package br.com.techthordev.leetcode.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.github")
public class GithubProperties {

    private String treeApiUrl;
    private String rawBaseUrl;
    private String owner;
    private String repo;
    private String branch;
    private String token;

    public String getTreeApiUrl() { return treeApiUrl; }
    public void setTreeApiUrl(String treeApiUrl) { this.treeApiUrl = treeApiUrl; }

    public String getRawBaseUrl() { return rawBaseUrl; }
    public void setRawBaseUrl(String rawBaseUrl) { this.rawBaseUrl = rawBaseUrl; }

    public String getOwner() { return owner; }
    public void setOwner(String owner) { this.owner = owner; }

    public String getRepo() { return repo; }
    public void setRepo(String repo) { this.repo = repo; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}