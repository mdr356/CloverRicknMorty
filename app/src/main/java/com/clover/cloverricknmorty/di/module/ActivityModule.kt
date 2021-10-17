package com.trinityempire.capsulenytimes.dagger.module

import com.clover.cloverricknmorty.ui.base.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity?
}