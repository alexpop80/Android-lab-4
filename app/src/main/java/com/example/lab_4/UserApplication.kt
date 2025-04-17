package com.example.lab_4

import android.app.Application
import com.example.lab_4.data.ApiClient
import com.example.lab_4.data.AppContainer

class UserApplication : Application() {
    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = ApiClient()
    }
}
