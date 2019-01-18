package com.sparrowhawkmobile.jamdemo.main

import com.sparrowhawkmobile.jamdemo.main.model.GithubRepoResult

interface MainActivityView {
    fun updateList(it: GithubRepoResult)
}