package com.clover.cloverricknmorty.app

import android.app.Application
import com.clover.cloverricknmorty.BuildConfig
import com.clover.cloverricknmorty.di.component.AppComponent
import com.clover.cloverricknmorty.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class MyApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        // This will initialise Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
            Timber.d("DEBUG MODE")
        }
        val mComponent: AppComponent = DaggerAppComponent.builder().application(this).context(this).build()
        mComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}
