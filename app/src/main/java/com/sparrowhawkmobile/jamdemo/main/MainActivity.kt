package com.sparrowhawkmobile.jamdemo.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparrowhawkmobile.jamdemo.main.model.GithubRepoResult
import com.sparrowhawkmobile.jamdemo.R
import com.sparrowhawkmobile.jamdemo.api.GithubApiManager
import com.sparrowhawkmobile.jamdemo.utils.addTo
import com.sparrowhawkmobile.jamdemo.utils.applyIOSubscribeMainThreadObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var disposables: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        createAdapter()
    }

    private fun createAdapter() {
        main_list_recycler_view.apply {
            adapter = GithubProjectsAdapter()
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onResume() {
        super.onResume()
        disposables = CompositeDisposable()
        testingApi()
    }

    private fun testingApi() {
        GithubApiManager.INSTANCE
            .getGithubApiClient()
            .getRepositoriesByQuery("tetris")
            .applyIOSubscribeMainThreadObserver()
            .subscribeBy(onError = { it.printStackTrace() }, onSuccess = { updateList(it) })
            .addTo(disposables)
    }

    private fun updateList(it: GithubRepoResult) {
        (main_list_recycler_view.adapter as GithubProjectsAdapter).updateItems(it.items)
    }
}
