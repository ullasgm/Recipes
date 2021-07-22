package com.example.recipes.presentation.detail

import android.os.Build
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.recipes.data.entity.Recipe
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class RecipeDetailActivityTest {

    private val recipe = Recipe(
        "Title",
        "photo",
        listOf("tags"),
        "description",
        0,
        "chef"
    )

    @get:Rule
    var activityScenarioRule = activityScenarioRule<RecipeDetailActivity>(
        RecipeDetailActivity.getIntent(
            ApplicationProvider.getApplicationContext(),
            recipe
        )
    )


    @Test
    fun `Test if The UI data was Set`() {

        val scenario = activityScenarioRule.scenario

        scenario.moveToState(Lifecycle.State.RESUMED)

        Shadows.shadowOf(Looper.getMainLooper()).idle()

        scenario.onActivity {
            it.binding.content.title.text = recipe.title
            it.binding.content.recipeDescription.text = recipe.description
            it.binding.content.chefName.text = recipe.chef

            assertEquals(recipe.title, it.binding.content.title.text)
            assertEquals(recipe.description, it.binding.content.recipeDescription.text)

        }

        scenario.close()
    }

}