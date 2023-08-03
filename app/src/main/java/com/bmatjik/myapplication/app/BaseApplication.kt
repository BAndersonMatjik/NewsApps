package com.bmatjik.myapplication.app

import android.app.Application
import android.content.pm.ApplicationInfo
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (applicationInfo.flags==ApplicationInfo.FLAG_DEBUGGABLE){
            Timber.plant(Timber.DebugTree())
        }
    }


}