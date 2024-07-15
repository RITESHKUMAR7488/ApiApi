package com.example.apiapi.models

data class Recipe(
    val limit: Int,
    val recipes: List<RecipeX>,
    val skip: Int,
    val total: Int
)