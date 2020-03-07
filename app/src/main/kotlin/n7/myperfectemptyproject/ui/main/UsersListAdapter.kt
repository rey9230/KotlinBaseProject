package n7.myperfectemptyproject.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import n7.myperfectemptyproject.data.source.local.model.User
import n7.myperfectemptyproject.databinding.ItemUserBinding

class UsersListAdapter : ListAdapter<User, UsersListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
    }

    class ViewHolder private constructor(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) = user.run {
            binding.user = this
            // we will call executePendingBindings() so that the list item is updated without waiting for the next layout phase.
            // This is required when using Data Binding inside RecyclerView, even if you are not using PrecomputedText.
            binding.executePendingBindings()
            // recommend trying PrecomputedText with list item TextViews that commonly display 200 characters or more
            // read about this https://medium.com/androiddevelopers/prefetch-text-layout-in-recyclerview-4acf9103f438
            // binding.tvDate.setTextFuture(PrecomputedTextCompat.getTextFuture(date!!.toString(), TextViewCompat.getTextMetricsParams(binding.tvDate), null))
        }

        fun clear() {
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return false
    }
}
