package com.sparrowhawkmobile.jamdemo

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
    val id: Long,
    val name: String
)