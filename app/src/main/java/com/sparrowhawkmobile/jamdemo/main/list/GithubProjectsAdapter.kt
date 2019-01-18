package com.sparrowhawkmobile.jamdemo.main.list

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.sparrowhawkmobile.jamdemo.main.model.GithubRepoItem


class GithubProjectsAdapter : ListDelegationAdapter<MutableList<GithubRepoItem>>() {
    init {
        delegatesManager.addDelegate(GithubProjectsDelagate())
        setItems(ArrayList())
    }

    fun updateItems(newItems: List<GithubRepoItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}

