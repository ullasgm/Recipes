package com.example.recipes.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.recipes.R
import com.example.recipes.data.entity.Recipe
import com.example.recipes.databinding.ActivityRecipeDetailBinding
import java.io.Serializable


class RecipeDetailActivity : AppCompatActivity() {

    companion object {
        private const val RECIPE = "recipe"
        fun getIntent(
            context: Context,
            recipe: Recipe
        ): Intent {
            val intent = Intent(context, RecipeDetailActivity::class.java)
            intent.putExtra(RECIPE, recipe as Serializable)
            return intent
        }

    }

    lateinit var binding: ActivityRecipeDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        }
        val recipe: Recipe = intent.getSerializableExtra(RECIPE) as Recipe
        val chefNamePlaceholder = resources.getString(R.string.shared_with_you_by)
        var chefName: String? = null
        binding.content.recipeDescription.text = recipe.description
        Glide.with(this)
            .load(recipe.photo)
            .centerCrop()
            .into(binding.content.recipeImage)
        if (!recipe.chef.isNullOrEmpty()) {
            chefName = chefNamePlaceholder + recipe.chef
        }
        binding.content.chefName.text = chefName
        binding.content.tags.adapter = TagsAdapter(recipe.tags)
        binding.content.title.text = recipe.title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)

    }


}