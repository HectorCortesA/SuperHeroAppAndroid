package com.example.superheroes

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response:String,

    @SerializedName("results") val superheroes: List<SuperHeroItemResponse>
)

data class SuperHeroItemResponse(
    @SerializedName("id" ) val superheroId: String,
    @SerializedName("name") val name: String,

)