package com.example.recipes.di

import android.content.Context
import com.contentful.java.cda.CDAClient
import com.example.recipes.data.ContentRepository
import com.example.recipes.data.Contentful
import com.example.recipes.presentation.RecipesApp
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    abstract fun bindContext(app: RecipesApp): Context

    @Binds
    abstract fun bindContentInfrastructure(contentful: Contentful): ContentRepository

    companion object {

        // For now hardcoding the values here.
        @Provides
        @Singleton
        fun provideCDI(): CDAClient =
            CDAClient.builder()
                .setSpace("kk2bw5ojx476")
                .setEnvironment("master")
                .setToken("7ac531648a1b5e1dab6c18b0979f822a5aad0fe5f1109829b8a197eb2be4b84c")
                .build()

        @Provides
        @Singleton
        fun provideGson(): Gson {
            return GsonBuilder().setPrettyPrinting().create()
        }
    }

}
