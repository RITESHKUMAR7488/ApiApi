package com.example.apiapi.uis

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apiapi.MyApplication
import com.example.apiapi.R
import com.example.apiapi.adapters.RecipeAdapter
import com.example.apiapi.apis.ApiInterface
import com.example.apiapi.databinding.ActivityMainBinding
import com.example.apiapi.models.Recipe
import com.example.apiapi.repositories.MyRepository
import com.example.apiapi.viewModel.MyViewModel
import com.example.apiapi.viewModelFactory.MyViewModelFactory
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(binding) {
            // ye code retrofit call karne ke liye hai

            val application = application as MyApplication
            val retrofitBuilder = application.retrofit
            val apiInterface = retrofitBuilder.create(ApiInterface::class.java)


            val repository = MyRepository(apiInterface)
            viewModel = ViewModelProvider(
                this@MainActivity,
                MyViewModelFactory(repository)
            )[MyViewModel::class.java]

            viewModel.getRecipes().observe(this@MainActivity) {

                val list = it.recipes

                val adapter = RecipeAdapter(list, this@MainActivity)
                recyclerView.adapter = adapter

            }



        }

    }
}