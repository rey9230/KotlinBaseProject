package n7.myperfectemptyproject.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import n7.myperfectemptyproject.BR

class DataBindingViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.user, item)
        binding.executePendingBindings()
    }
}
