package com.example.recipes.presentation.list


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.recipes.data.ContentRepository
import com.example.recipes.data.entity.Recipe
import com.example.recipes.dependencies.Dependencies
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RecipeListViewModelTest {


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var recipeListViewModel: RecipeListViewModel


    private lateinit var dependencies: Dependencies


    private lateinit var contentRepository: ContentRepository


    @Before
    fun setUP() {
        contentRepository = mock(ContentRepository::class.java)
    }

    private val recipe: List<Recipe> =
        listOf(
            Recipe(
                "Title",
                "photo",
                listOf("tags"),
                "description",
                0,
                "chef"
            ),
            Recipe(
                "Title",
                "photo",
                listOf("tags"),
                "description",
                0,
                "chef"

            )
        )

    @Test
    @Ignore
    fun testGetRecipeData() {
        mainCoroutineRule.runBlockingTest {
            val isModified: (List<Recipe>) -> Unit = {}
            isModified(recipe)
            `when`(contentRepository.fetchAllRecipes(errorCallback = ::error) {}).thenReturn(
                isModified(recipe)
            )
            dependencies = Dependencies(contentRepository)
            recipeListViewModel = RecipeListViewModel(dependencies)
            recipeListViewModel.getList()

            val result = recipeListViewModel.recipeData.getOrAwaitValue().find {
                it.title == "Title"
            }
            assertNotNull(result)
        }
    }
}