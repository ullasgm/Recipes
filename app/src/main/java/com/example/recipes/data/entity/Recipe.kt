package com.example.recipes.data.entity

import java.io.Serializable

data class Recipe(
    val title: String,
    val photo: String,
    val tags: List<String>,
    val description: String,
    val calories: Int,
    val chef: String?
) : Serializable

