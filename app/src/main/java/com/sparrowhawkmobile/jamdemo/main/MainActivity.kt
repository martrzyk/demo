package com.sparrowhawkmobile.jamdemo.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparrowhawkmobile.jamdemo.R
import com.sparrowhawkmobile.jamdemo.main.list.GithubProjectsAdapter
import com.sparrowhawkmobile.jamdemo.main.model.GithubRepoResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), MainActivityView {

    private val presenter: MainActivityPresenter by lazy { MainActivityPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        presenter.onInit()
    }

    private fun initUI() {
        setSupportActionBar(toolbar)
        initRecycler()
    }

    private fun initRecycler() {
        main_list_recycler_view.apply {
            adapter = GithubProjectsAdapter()
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun updateList(it: GithubRepoResult) {
        (main_list_recycler_view.adapter as GithubProjectsAdapter).updateItems(it.items)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
