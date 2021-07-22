package com.example.recipes.di

import com.example.recipes.presentation.detail.RecipeDetailActivity
import com.example.recipes.presentation.list.RecipeListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class BuilderModule {

    @ContributesAndroidInjector(modules = [RecipeDetailActivityModule::class])
    abstract fun bindRecipeDetailActivity(): RecipeDetailActivity

    @ContributesAndroidInjector(modules = [RecipeListActivityModule::class])
    abstract fun bindRecipeListActivity(): RecipeListActivity
}

@Module
class RecipeDetailActivityModule

@Module
class RecipeListActivityModule