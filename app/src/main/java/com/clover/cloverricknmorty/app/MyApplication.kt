package com.clover.cloverricknmorty.app

import android.app.Application
import com.clover.cloverricknmorty.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}