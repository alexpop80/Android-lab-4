package com.example.lab_4.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// get all the data need form the website
data class UserResponse(
    @SerializedName("results")
    val results: List<Urlinfo>
)

data class Urlinfo(
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: Name,
    @SerializedName("picture")
    val picture: Picture
)

data class Picture(
    @SerializedName("large")
    val large: String
)
data class Name (
    @SerializedName("title")
    val title: String,
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String
)