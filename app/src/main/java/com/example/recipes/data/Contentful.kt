package com.example.recipes.data

import com.contentful.java.cda.CDAClient
import com.contentful.java.cda.CDAEntry
import com.example.recipes.data.entity.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

private const val DEFAULT_LOCALE = "en-US"
private const val CONTENT_TYPE_RECIPE = "recipe"

open class Contentful @Inject constructor(
    private var client: CDAClient
) : ContentRepository {
    override suspend fun fetchAllRecipes(
        errorCallback: (Throwable) -> Unit,
        successCallback: (List<Recipe>) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            try {
                val recipes =
                    client
                        .fetch(CDAEntry::class.java)
                        .withContentType(CONTENT_TYPE_RECIPE)
                        .all()
                        .items()
                        .map {
                            fromRestEntry(
                                it as CDAEntry,
                                DEFAULT_LOCALE
                            )
                        }
                successCallback(recipes)
            } catch (throwable: Throwable) {
                errorCallback(throwable)
            }
        }
    }

}

