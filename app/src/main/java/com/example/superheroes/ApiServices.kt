package com.example.superheroes

import android.adservices.adid.AdId
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("/api/b0a159ed4417bfd6659711348cab5b62/search/{name}")  //La ruta de enpoint
    suspend fun getSuperheroes(@Path("name") superheroName:String):Response<SuperHeroDataResponse>
    //se crear el path para que busque la ruta
    //todo lo que se llame name lo buscara

    @GET("/api/b0a159ed4417bfd6659711348cab5b62/{id}")
    suspend fun  getSuperheroDetail(@Path("id") superheroId: String):Response<SuperheroRetailResponse>
}