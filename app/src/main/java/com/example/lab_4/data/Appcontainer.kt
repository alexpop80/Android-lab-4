package com.example.lab_4.data


import com.example.lab_4.model.Urlinfo
import com.example.lab_4.network.ApiUser
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Appcontainer whole thing is to set up the url link to website with all of the infomation
interface AppContainer {
    val userDirectoryrep: UserDirectoryrep
}

class ApiClient : AppContainer {
    private val URL = "https://randomuser.me/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(URL)
        .build()

    private val retrofitService: ApiUser by lazy {
        retrofit.create(ApiUser::class.java)
    }

    override val userDirectoryrep: UserDirectoryrep  by lazy {
        NetworkUser(retrofitService)
    }
}

