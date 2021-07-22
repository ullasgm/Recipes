package com.example.recipes.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.TagsListItemBinding


class TagsAdapter(
    private val list: List<String?>
) : RecyclerView.Adapter<TagsAdapter.TagsViewHolder>() {

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        return TagsViewHolder(
            TagsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.root.tag = position
    }

    inner class TagsViewHolder(internal val binding: TagsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name = binding.textview
        fun bind(data: String?) {
            name.text = data
        }
    }

}

