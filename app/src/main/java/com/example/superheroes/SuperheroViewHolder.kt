package com.example.superheroes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResponse: SuperHeroItemResponse, onItemSelect:(String)-> Unit){ //Esto recibe los items
        binding.tvSuperName.text = superHeroItemResponse.name //Carga el texto

        Picasso.get().load(superHeroItemResponse.superheroImage.url).into(binding.ivSuperHero)
        //Trameme picaso y carga en esta url en en el vinculo de de la image de layout
        binding.root.setOnClickListener{onItemSelect(superHeroItemResponse.superheroId)}
    }

}