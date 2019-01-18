package com.sparrowhawkmobile.jamdemo.main

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparrowhawkmobile.jamdemo.main.list.GithubProjectsAdapter
import com.sparrowhawkmobile.jamdemo.main.model.GithubRepoResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import androidx.recyclerview.widget.DividerItemDecoration
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sparrowhawkmobile.jamdemo.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MainActivityView {

    private val presenter: MainActivityPresenter by lazy { MainActivityPresenter(this) }
    private val disposable: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        presenter.onInit()
    }

    private fun initUI() {
        initRecycler()
        initSearcher()
    }

    private fun initSearcher() {
        RxTextView.textChanges(search_box as EditText)
            .filter { it.length > 3 }
            .debounce(300, TimeUnit.MILLISECONDS)
            .map { it.toString() }
            .subscribeBy(
                onError = { it.printStackTrace() },
                onNext = { presenter.filterByString(it) })
            .addTo(disposable)
    }

    private fun initRecycler() {
        main_list_recycler_view.apply {
            adapter = GithubProjectsAdapter()
            layoutManager = LinearLayoutManager(context)
            main_list_recycler_view.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }


    override fun updateList(it: GithubRepoResult) {
        (main_list_recycler_view.adapter as GithubProjectsAdapter).updateItems(it.items)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
        disposable.clear()
    }
}
