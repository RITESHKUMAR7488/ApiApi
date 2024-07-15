package com.example.apiapi.uis

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.apiapi.R
import com.example.apiapi.databinding.ActivityDescriptionBinding
import com.example.apiapi.models.RecipeX

class Description : AppCompatActivity() {
    private lateinit var binding: ActivityDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this@Description, R.layout.activity_description)
        with(binding) {
            val model=intent.getSerializableExtra("model")as RecipeX
            Log.d("modelss",model.toString())
            tvName.text=model.name
            tvIngredients.text=model.ingredients.toString()
            tvInstruction.text=model.instructions.toString()
            Glide.with(this@Description).load(model.image).into(recipeImageDes)


        }
    }
}