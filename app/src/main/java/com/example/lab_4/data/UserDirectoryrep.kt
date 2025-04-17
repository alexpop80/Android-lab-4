package com.example.lab_4.data


import com.example.lab_4.model.Urlinfo
import com.example.lab_4.network.ApiUser

/**
 * Repository that fetch Urlinfo
 */
interface UserDirectoryrep {
    /** Fetches list of Urlinfo in apiuser */
    suspend fun getUserInfo(): List<Urlinfo>?
}

/**
 * Network Implementation of Repository that fetch urlinfo list from Apiuser.
 */
class NetworkUser(
    private val apiUser: ApiUser
) : UserDirectoryrep {
    /** Fetches list of Urlinfo in apiuser */
    override suspend fun getUserInfo(): List<Urlinfo>? = apiUser.getInfo().body()?.results
}
