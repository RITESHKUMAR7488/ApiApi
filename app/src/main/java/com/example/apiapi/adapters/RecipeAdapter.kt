package com.example.apiapi.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apiapi.uis.Description
import com.example.apiapi.databinding.ItemViewBinding
import com.example.apiapi.models.RecipeX

class RecipeAdapter(private val recipes: List<RecipeX>, private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecipeViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return recipes.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder: RecipeViewHolder = holder as RecipeViewHolder
        val currentItem = recipes[position]
        with(viewHolder.binding) {
            Glide.with(context).load(currentItem.image).into(imageRecipe)
            nameRecipe.text = currentItem.name
            cardItem.setOnClickListener {
                val intent = Intent(context, Description::class.java)
                intent.putExtra("model", currentItem)
                context.startActivity(intent)

            }

        }


    }

    inner class RecipeViewHolder(val binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}