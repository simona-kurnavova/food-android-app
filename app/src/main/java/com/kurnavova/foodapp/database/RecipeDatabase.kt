package com.kurnavova.foodapp.database

import com.kurnavova.foodapp.database.ApiClient.client
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RecipeDatabase {
    @Provides
    @Singleton
    fun provideRecipeDAO(): RecipeDao = client.create(RecipeDao::class.java)
}