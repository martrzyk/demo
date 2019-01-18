package com.sparrowhawkmobile.jamdemo.main.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubRepoResult(
    @JsonProperty("total_count") val totalCount: Long,
    @JsonProperty("incomplete_results") val incompleteResults: Boolean,
    @JsonProperty("items") val items: List<GithubRepoItem>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubRepoItem(
    @JsonProperty("id") val id: Long,
    @JsonProperty("name") val name: String,
    @JsonProperty("size") val repoSize: Int,
    @JsonProperty("owner") val owner: GithubRepoItemOwner,
    @JsonProperty("has_wiki") val hasWiki: Boolean
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubRepoItemOwner(
    @JsonProperty("id") val id: Long,
    @JsonProperty("login") val login: String
)