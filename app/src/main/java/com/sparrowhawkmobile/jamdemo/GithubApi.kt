package com.sparrowhawkmobile.jamdemo

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubApi {

    @GET("search/repositories")
    fun getRepositoriesByQuery(
        @Query("q") queryText: String
    ): Single<GithubRepoResult>
}
