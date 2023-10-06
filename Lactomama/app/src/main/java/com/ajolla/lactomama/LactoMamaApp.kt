package com.ajolla.lactomama

import android.app.Application
import android.content.Context

class LactoMamaApp: Application(){
    companion object{
        lateinit var appContext: Context
    }    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}