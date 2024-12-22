package com.example.superheroes

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.superheroes.DetailSuperheroActivity.Companion.EXTRA_ID
import com.example.superheroes.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var retrofit: Retrofit
    private lateinit var  binding: ActivityMainBinding

    private lateinit var adapter: SuperheroAdapter

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
        adapter = SuperheroAdapter{navigateToDetail(it)} //Envia el bloque de navigate
        binding.rvSuperhero.setHasFixedSize(true)
        binding.rvSuperhero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperhero.adapter = adapter

    }
    private fun searchByName(query: String){
        binding.progressBar.isVisible = true // mientras se hace la bsuqueda aparece el bar
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiServices::class.java).getSuperheroes(query)
            if(myResponse.isSuccessful){
                Log.i("aaaa", "Funiona")
                val response: SuperHeroDataResponse? = myResponse.body()
                if(response !=null){
                    Log.i("aaaa", response.toString())
                    runOnUiThread{ // Corre en el hilo principal lo que eeste aqui adentro
                        adapter.updateList(response.superheroes) // esta funcion es para cuando se cargue el update si cambio algo en el archivo de Adapter
                        binding.progressBar.isVisible = false // Se hace invisible
                    }
                }
            }else{
                //Cuando aqui no colocar nada se crashea ya que no encuentra super herues por eso se tiene que poner
                //

                Log.i("aaaa", "No funciona")
            }
        }
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id:String){
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID,id) //Clave y una id , hay crear una constante en la clave, Se importo la constante de DetailSupeheroActivity
        startActivity(intent)
    }
}







