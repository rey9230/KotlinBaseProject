package n7.myperfectemptyproject.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import n7.myperfectemptyproject.databinding.ItemSimpleBinding
import n7.myperfectemptyproject.ui.main.domain.model.MainModel

class SimpleListAdapter : ListAdapter<MainModel, SimpleListAdapter.ViewHolder>(DiffCallback()) {

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

    class ViewHolder private constructor(private val binding: ItemSimpleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(mainModel: MainModel) {
            binding.executePendingBindings()
//            binding.tvItemUserTitle.setTextFuture(PrecomputedTextCompat.getTextFuture(userInfo.name.title,binding.tvItemUserTitle.textMetricsParamsCompat,null))
        }

        fun clear() {
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSimpleBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<MainModel>() {

    override fun areItemsTheSame(oldItem: MainModel, newItem: MainModel): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: MainModel, newItem: MainModel): Boolean {
        return true
    }
}
