package com.example.apiapi.apis

import com.example.apiapi.models.Recipe
import retrofit2.Call
import retrofit2.http.GET


interface ApiInterface {
    @GET("recipes")
    fun getRecipes(): Call<Recipe>

}