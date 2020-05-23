package n7.myperfectemptyproject.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import n7.myperfectemptyproject.databinding.ItemLoadingBinding

class LoadingAdapter : RecyclerView.Adapter<LoadingAdapter.ViewHolder>() {

    private val list: MutableList<Int> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemViewType(position: Int): Int = 0

    fun isLoading(loading: Boolean) {
        list.clear()
        if (loading) list.add(1)
        notifyItemChanged(0)
        notifyItemRemoved(0)
        notifyItemInserted(0)
    }

    class ViewHolder private constructor(
        binding: ItemLoadingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        companion object {

            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}