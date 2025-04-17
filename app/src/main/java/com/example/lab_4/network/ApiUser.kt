package com.example.lab_4.network

import com.example.lab_4.model.Picture
import com.example.lab_4.model.Urlinfo
import com.example.lab_4.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

//the is the rest of the website
interface ApiUser {
    @GET("api/?results=20")
    //this get the data
    suspend fun getInfo(): Response<UserResponse>
}