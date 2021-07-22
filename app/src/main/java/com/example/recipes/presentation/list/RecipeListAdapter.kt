package com.example.recipes.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipes.data.entity.Recipe
import com.example.recipes.databinding.RecipeListItemBinding
import com.example.recipes.presentation.list.RecipeListAdapter.RecipeListViewHolder

class RecipeListAdapter : ListAdapter<Recipe, RecipeListViewHolder>(RecipeDiff) {

    private var listener: ItemClicked? = null

    companion object {
        val RecipeDiff = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe) = oldItem == newItem
            override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe) =
                oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        return RecipeListViewHolder(
            RecipeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun setCustomButtonListener(listener: ItemClicked) {
        this.listener = listener
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.binding.root.tag = position
    }

    inner class RecipeListViewHolder(internal val binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        val name = binding.recipeTitle
        private val image = binding.recipeImage

        fun bind(data: Recipe) {
            name.text = data.title
            val url = data.photo
            Glide.with(binding.root.context)
                .load(url)
                .centerCrop()
                .into(image)
            binding.root.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            val position = v!!.tag as Int
            listener?.onClickedItem(v, position)
        }

    }

    interface ItemClicked {
        fun onClickedItem(item: View?, position: Int)
    }
}