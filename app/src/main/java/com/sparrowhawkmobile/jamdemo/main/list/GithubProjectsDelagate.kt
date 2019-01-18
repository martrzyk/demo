package com.sparrowhawkmobile.jamdemo.main.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.sparrowhawkmobile.jamdemo.R
import com.sparrowhawkmobile.jamdemo.main.model.GithubRepoItem
import com.sparrowhawkmobile.jamdemo.utils.inflate
import kotlinx.android.synthetic.main.simple_item.view.*

class GithubProjectsDelagate : AdapterDelegate<MutableList<GithubRepoItem>>() {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = GithubProjectHolder(parent)

    override fun isForViewType(items: MutableList<GithubRepoItem>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: MutableList<GithubRepoItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        with(holder as GithubProjectHolder) {
            val item = items[position]
            val backgroundColor =
                if (item.hasWiki) R.color.hasWikiColor else R.color.NoWikiColor

            itemView.name_of_repo_tv.text = item.name
            itemView.size_of_repo_tv.text = item.repoSize.toString()
            itemView.login_of_user_tv.text = item.owner.login

            itemView.setBackgroundColor(itemView.context.getColor(backgroundColor))
        }
    }

    inner class GithubProjectHolder(itemView: ViewGroup) :
        RecyclerView.ViewHolder(itemView.inflate(R.layout.simple_item))
}