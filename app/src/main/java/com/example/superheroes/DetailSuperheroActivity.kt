package com.example.superheroes

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.superheroes.databinding.ActivityDetailSuperheroBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.reflect.Type
import kotlin.math.roundToInt

class DetailSuperheroActivity : AppCompatActivity() {
    companion object{
        //Los datos de aqui se pueden acceder a otras pantallas
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var binding: ActivityDetailSuperheroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id:String = intent.getStringExtra(EXTRA_ID).orEmpty() //Se recupera el ID, si el valor  no es String devuelveme un nulo empty
        getSuperheroInformation(id)
    }
    private fun getSuperheroInformation(id: String){
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetail =
            getRetrofit().create(ApiServices::class.java).getSuperheroDetail(id)

            if (superheroDetail.body() !== null) {
                runOnUiThread { createUI(superheroDetail.body()!!) }

            }
        }
    }

    private fun createUI(superhero:SuperheroRetailResponse) {
    Picasso.get().load(superhero.image.url).into(binding.ivSuperhero) //Cargar la imagen
        binding.tvSuperheroName.text = superhero.name //Cargar el texto
        prepareStats(superhero.powerstats)
        binding.tvSuperRealName.text = superhero.biography.fullName
        binding.tvSuperheroPublisher.text = superhero.biography.publisher

    }

    private fun prepareStats(powerstats: PowerStarResponse) {
        updateHeight(binding.viewIntelligence, powerstats.intelligence)
        updateHeight(binding.viewStrength, powerstats.strength)
        updateHeight(binding.viewSpeed, powerstats.speed)
        updateHeight(binding.viewDurability, powerstats.durability)
        updateHeight(binding.viewPower, powerstats.power)
        updateHeight(binding.viewCombat, powerstats.combat)


       /*
       Para no estar escribiendo esto varias veces mejor haces un metodo que contega todo eso updateHeight
       val params = binding.viewCombat.layoutParams
        params.height = powerstats.combat.toInt()
        binding.viewCombat.layoutParams = params
        */

    }
    private fun updateHeight(view: View, stats:String){
        val params = view.layoutParams
        params.height = pxToDp(stats.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px: Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}