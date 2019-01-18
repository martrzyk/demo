package com.sparrowhawkmobile.jamdemo.api

import com.sparrowhawkmobile.jamdemo.main.model.GithubRepoResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubApi {

    @GET("search/repositories")
    fun getRepositoriesByQuery(
        @Query("q") queryText: String,
        @Query("per_page") perPage: Int = 100
    ): Single<GithubRepoResult>
}
