package com.example.apiapi.uis

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.apiapi.R
import com.example.apiapi.adapters.RecipeAdapter
import com.example.apiapi.apis.ApiInterface
import com.example.apiapi.databinding.ActivityMainBinding
import com.example.apiapi.models.Recipe
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(binding) {
            val retrofitBuilder = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)

            val retrofitData = retrofitBuilder.getRecipes()

            retrofitData.enqueue(object : Callback<Recipe?> {
                override fun onResponse(
                    p0: retrofit2.Call<Recipe?>,
                    p1: retrofit2.Response<Recipe?>
                ) {
                    val list = p1.body()?.recipes
                    val adapter = list?.let { RecipeAdapter(it, this@MainActivity) }
                    recyclerView.adapter = adapter

                }


                override fun onFailure(p0: retrofit2.Call<Recipe?>, p1: Throwable) {
                    Toast.makeText(this@MainActivity, "p1.message", Toast.LENGTH_SHORT).show()
                }

            })


        }

    }
}