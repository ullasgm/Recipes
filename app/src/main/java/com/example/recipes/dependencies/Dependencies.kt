package com.example.recipes.dependencies

import com.example.recipes.data.ContentRepository
import javax.inject.Inject

class Dependencies @Inject constructor(
    val contentInfrastructure: ContentRepository
)