package com.sparrowhawkmobile.jamdemo.main.interactors

import com.sparrowhawkmobile.jamdemo.api.GithubApiManager
import com.sparrowhawkmobile.jamdemo.main.model.GithubRepoResult
import com.sparrowhawkmobile.jamdemo.utils.applyIOSubscribeMainThreadObserver
import io.reactivex.Single

class GetRepositoriesInteractor {
    fun execute(query: String): Single<GithubRepoResult> =
        GithubApiManager.INSTANCE
            .getGithubApiClient()
            .getRepositoriesByQuery(query)
            .applyIOSubscribeMainThreadObserver()
}