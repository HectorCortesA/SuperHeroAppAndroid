package com.example.superheroes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.databinding.ItemSuperheroBinding

class SuperheroViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResponse: SuperHeroItemResponse){ //Esto recibe los items
        binding.tvSuperName.text = superHeroItemResponse.name
    }

}