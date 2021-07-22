package com.example.recipes.di

import com.example.recipes.presentation.RecipesApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AndroidInjectionModule::class, BuilderModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: RecipesApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: RecipesApp)

}
