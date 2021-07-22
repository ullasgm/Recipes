package com.example.recipes.presentation.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.recipes.R
import com.example.recipes.data.entity.Recipe
import com.example.recipes.databinding.ActivityRecipeListBinding
import com.example.recipes.di.ViewModelFactory
import com.example.recipes.presentation.MaterialDialog
import com.example.recipes.presentation.detail.RecipeDetailActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class RecipeListActivity : AppCompatActivity(), RecipeListAdapter.ItemClicked {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<RecipeListViewModel>

    private lateinit var viewModel: RecipeListViewModel

    private var listData: List<Recipe> = emptyList()
    lateinit var binding: ActivityRecipeListBinding
    val adapter = RecipeListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RecipeListViewModel::class.java)
        binding.recipesRecycler.adapter = adapter
        viewModel.getList()
        viewModel.getRecipeDataObserver().observe(this, {
            binding.loadingIndicator.visibility = View.GONE
            listData = it
            adapter.submitList(listData)
        })
        viewModel.getError().observe(this, {
            MaterialDialog.showDialog(
                R.string.no_network_name, R.string.no_network_error, this, false
            ) {
                viewModel.getList()
            }
        })
        adapter.setCustomButtonListener(this)
    }

    override fun onClickedItem(item: View?, position: Int) {
        startActivity(
            RecipeDetailActivity.getIntent(
                this, listData[position]
            )
        )
    }
}