package n7.myperfectemptyproject.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference
import java.util.Collections
import n7.myperfectemptyproject.R
import n7.myperfectemptyproject.databinding.ItemUserBinding
import n7.myperfectemptyproject.ui.main.domain.vo.VOUser
import n7.myperfectemptyproject.utils.extension.animateRotation
import n7.myperfectemptyproject.utils.extension.animateTranslationX

// RecyclerView optimizations https://youtu.be/GZkTwgetUWI
class UsersListAdapter : ListAdapter<VOUser, UsersListAdapter.ViewHolder>(DiffCallback()) {

    private val activeViewHolders = ArrayList<WeakReference<ViewHolder>>()
    private val listener = View.OnClickListener {
        it.isSelected = !it.isSelected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    fun swap(swapFrom: Int, swapTo: Int) {
        val mutableList = currentList.toMutableList()
        Collections.swap(mutableList, swapFrom, swapTo)
        submitList(mutableList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        activeViewHolders.add(WeakReference(holder))
        holder.bind(getItem(position))
        holder.animateRotation()
        holder.animateTranslationX()
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        for (i in 0 until activeViewHolders.size - 1) {
            val currentViewHolder = activeViewHolders[i].get()
            if (currentViewHolder == null || currentViewHolder == holder) {
                activeViewHolders.removeAt(i)
            }
        }
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_user

    // in this method we should always stop different animation that happens on our viewHolder
    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
    }

    private fun navigateTo(binding: ViewDataBinding) {
        val extra = FragmentNavigatorExtras(
            binding.root to binding.root.transitionName
        )
        binding.root.findNavController().navigate(R.id.coil_bitmap)
    }

    class ViewHolder private constructor(
        private val binding: ViewDataBinding,
        private val listener: View.OnClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        // can we animate our items? step brother? https://youtu.be/7LqeFDsi5dQ
        fun bind(user: VOUser) = user.run {
            (binding as ItemUserBinding).user = this
            // we will call executePendingBindings() so that the list item is updated without waiting for the next layout phase.
            // This is required when using Data Binding inside RecyclerView, even if you are not using PrecomputedText.
            binding.executePendingBindings()
            // recommend trying PrecomputedText with list item TextViews that commonly display 200 characters or more
            // read about this https://medium.com/androiddevelopers/prefetch-text-layout-in-recyclerview-4acf9103f438
            // binding.tvDate.setTextFuture(PrecomputedTextCompat.getTextFuture(date!!.toString(), TextViewCompat.getTextMetricsParams(binding.tvDate), null))

            binding.root.setOnClickListener(listener)
        }

        fun clear() {
            binding.root.setOnClickListener(null)
        }

        companion object {
            fun from(
                parent: ViewGroup,
                listener: View.OnClickListener
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, listener)
            }
        }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<VOUser>() {

    override fun areItemsTheSame(oldItem: VOUser, newItem: VOUser): Boolean {
        return oldItem.firstName == newItem.firstName
    }

    override fun areContentsTheSame(oldItem: VOUser, newItem: VOUser): Boolean {
        return true
    }
}

class ItemMoveCallBack(private val viewModel: UsersListAdapter) : ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN or ItemTouchHelper.UP, 0) {
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        val fromPos = viewHolder.layoutPosition
        val toPos = target.layoutPosition

        viewModel.swap(fromPos, toPos)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }
}
