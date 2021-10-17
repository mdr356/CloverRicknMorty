package com.trinityempire.capsulenytimes.dagger.module

import com.clover.cloverricknmorty.ui.details.view.DetailsFragment
import com.clover.cloverricknmorty.ui.main.view.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun bindMainFragment() : MainFragment

    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment
}