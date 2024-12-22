package com.example.superheroes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class SuperheroAdapter (
    var superheroList:List<SuperHeroItemResponse> = emptyList(),
    private val onItemSelect:(String)-> Unit  //Recibe una funcion landa encasuplada , el metodo navigate es tenerlo en  onItemSelect es
) :
    RecyclerView.Adapter<SuperheroViewHolder>() {

        fun updateList(superheroList: List<SuperHeroItemResponse>){
            this.superheroList = superheroList
            notifyDataSetChanged() //Recargar para vver si los datos haya cambaido
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        return SuperheroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )
    }

    override fun onBindViewHolder(viewholder: SuperheroViewHolder, position: Int) {
        viewholder.bind(superheroList[position], onItemSelect)
    }

    override fun getItemCount(): Int = superheroList.size

}