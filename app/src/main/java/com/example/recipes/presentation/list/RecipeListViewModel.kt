package com.example.recipes.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.entity.Recipe
import com.example.recipes.dependencies.Dependencies
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(
    private val dependencies: Dependencies
) : ViewModel() {

    var recipeData = MutableLiveData<List<Recipe>>()

    var errorAction = MutableLiveData<Throwable>()

    fun getRecipeDataObserver(): LiveData<List<Recipe>> = recipeData

    fun getError(): LiveData<Throwable> = errorAction

    fun getList() {
        viewModelScope.launch {
            dependencies.contentInfrastructure.fetchAllRecipes(
                { errorAction.postValue(it) },
                { recipeData.postValue(it) }
            )

        }
    }

}
