package com.example.recipes.presentation.list

import android.os.Build
import android.os.Looper
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recipes.data.entity.Recipe
import com.example.recipes.presentation.list.RecipeListAdapter.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class RecipeListActivityTest {
    private val recipe: List<Recipe> =
        listOf(
            Recipe(
                "Title",
                "photo",
                listOf("tags"),
                "description",
                0,
                "chef"
            )
        )

    @get:Rule
    var activityScenarioRule = activityScenarioRule<RecipeListActivity>()

    @Test
    fun `Test for setting data In List UI`() {
        val scenario = activityScenarioRule.scenario

        scenario.moveToState(Lifecycle.State.RESUMED)

        scenario.onActivity {
            it.adapter.submitList(recipe)
            it.binding.loadingIndicator.visibility = View.GONE
        }

        Shadows.shadowOf(Looper.getMainLooper()).idle()

        scenario.onActivity {
            Assert.assertTrue(it.binding.recipesRecycler.isVisible)
            Assert.assertTrue(it.binding.loadingIndicator.isGone)
            val holder =
                it.binding.recipesRecycler.findViewHolderForAdapterPosition(0) as RecipeListViewHolder
            assertEquals(recipe.first().title, holder.binding.recipeTitle.text.toString())
        }

        scenario.close()
    }

}