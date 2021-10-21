package com.clover.cloverricknmorty.di.component

import android.app.Application
import android.content.Context
import com.clover.cloverricknmorty.app.MyApplication
import com.clover.cloverricknmorty.di.module.AppModule
import com.clover.cloverricknmorty.di.module.NetworkModule
import com.clover.cloverricknmorty.di.module.RepositoryModule
import com.clover.cloverricknmorty.di.module.ViewModelModule
import com.trinityempire.capsulenytimes.dagger.module.ActivityModule
import com.trinityempire.capsulenytimes.dagger.module.FragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    FragmentModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    ViewModelModule::class,
])

interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}
