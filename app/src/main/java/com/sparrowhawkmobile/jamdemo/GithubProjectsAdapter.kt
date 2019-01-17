package com.sparrowhawkmobile.jamdemo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import kotlinx.android.synthetic.main.simple_item.view.*


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

class GithubProjectsDelagate : AdapterDelegate<MutableList<GithubRepoItem>>() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = GithubProjectHolder(parent)

    override fun isForViewType(items: MutableList<GithubRepoItem>, position: Int): Boolean = true

    override fun onBindViewHolder(items: MutableList<GithubRepoItem>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        with(holder as GithubProjectHolder) {
            val item = items[position]
            itemView.text.text = item.name
        }
    }

    inner class GithubProjectHolder(itemView: ViewGroup) :
        RecyclerView.ViewHolder(itemView.inflate(R.layout.simple_item))
}
