package com.sparrowhawkmobile.jamdemo.main

import com.sparrowhawkmobile.jamdemo.main.interactors.GetRepositoriesInteractor
import com.sparrowhawkmobile.jamdemo.main.model.GithubRepoResult
import com.sparrowhawkmobile.jamdemo.utils.addTo
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class MainActivityPresenter(private val view: MainActivityView) {
    private val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    fun onInit() {
        getDefaultRepositories()
    }

    private fun getDefaultRepositories() {
        GetRepositoriesInteractor()
            .execute(defaultQuery)
            .subscribeBy(onError = this::onDownloadError, onSuccess = this::onDownloadSuccess)
            .addTo(disposables)
    }

    private fun onDownloadError(it: Throwable) {
        it.printStackTrace()
    }

    private fun onDownloadSuccess(it: GithubRepoResult) {
        view.updateList(it)
    }

    fun onDestroy() {
        disposables.clear()
    }

    companion object {
        private const val defaultQuery = "tetris"
    }
}
