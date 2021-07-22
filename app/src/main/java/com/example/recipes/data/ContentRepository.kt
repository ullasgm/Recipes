package com.example.recipes.data

import com.example.recipes.data.entity.Recipe

interface ContentRepository {

    suspend fun fetchAllRecipes(
        errorCallback: (Throwable) -> Unit,
        successCallback: (List<Recipe>) -> Unit
    )
}