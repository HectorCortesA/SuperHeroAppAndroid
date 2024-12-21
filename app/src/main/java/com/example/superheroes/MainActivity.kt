package com.example.superheroes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroes.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retrofit = getRetrofit()
        binding = ActivityMainBinding.inflate(layoutInflater) //atributo que ya pose la clase
        setContentView(binding.root)
        //Esto sirve  para ya no declarar variables y ya los inyecte solo
        InitUI()


    }
    private fun InitUI(){
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //Esta funcion es para cuando le demos en el boton de buscar
                searchByName(query.orEmpty()) //Devuelve todo los superheroes
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
                //Esta funcion es cuando vayamos escribiendo
            }
        })
    }
    private fun searchByName(query: String){

    }

    private fun getRetrofit(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}







