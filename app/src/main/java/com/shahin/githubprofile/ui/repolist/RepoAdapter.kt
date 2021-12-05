package com.shahin.githubprofile.ui.repolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shahin.data.model.RepoItem
import com.shahin.githubprofile.databinding.ItemRepoListBinding

class RepoAdapter(val lifecycleOwner: LifecycleOwner) :
    ListAdapter<RepoItem, RepoAdapter.RepoViewHolder>(RepoListDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder =
        RepoViewHolder(
            ItemRepoListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply { lifecycleOwner = this@RepoAdapter.lifecycleOwner }
        )

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RepoViewHolder(val binding: ItemRepoListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepoItem) {
            binding.run {
                title = item.name
                description = item.description
                createdAt = item.createdAt
            }
        }
    }

}

class RepoListDiffUtilCallback : DiffUtil.ItemCallback<RepoItem>() {
    override fun areItemsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RepoItem, newItem: RepoItem): Boolean =
        oldItem == newItem
}