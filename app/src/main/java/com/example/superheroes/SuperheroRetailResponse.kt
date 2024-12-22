package com.example.superheroes

import com.google.gson.annotations.SerializedName

data class SuperheroRetailResponse(
    @SerializedName("name") val name:String,
    @SerializedName("powerstats") val powerstats:PowerStarResponse,
    @SerializedName("image") val image:SuperheroImageDetailResponse,
    @SerializedName("biography") val biography:BiographyDetailsResponse)


data class PowerStarResponse(
    @SerializedName("intelligence") val intelligence:String,
    @SerializedName("strength") val strength:String,
    @SerializedName("speed") val  speed:String,
    @SerializedName("durability") val  durability:String,
    @SerializedName("power") val  power:String,
    @SerializedName("combat") val  combat:String
)

data class SuperheroImageDetailResponse(
    @SerializedName("url") val url:String)

data class BiographyDetailsResponse(
    @SerializedName("full-name") val fullName:String,
    @SerializedName("publisher") val publisher:String
)