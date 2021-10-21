package com.clover.cloverricknmorty.di.module


import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.ui.details.viewmodel.DetailsViewModel
import com.clover.cloverricknmorty.ui.main.viewmodel.MainViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass


class ViewModelFactory @Inject constructor(private val mProviderMap: MutableMap<Class<out ViewModel>,
        Provider<ViewModel>>) :
    ViewModelProvider.Factory {
    @NonNull
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = mProviderMap[modelClass]?.get() as T

}
@Module
class ViewModelModule {
    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    fun viewModelFactory(providerMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelFactory =
        ViewModelFactory(providerMap)


    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideGridViewModel(mainRepository: MainRepository): ViewModel {
        return MainViewModel(mainRepository)
    }

    @Provides
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    fun provideDetailsViewModel(mainRepository: MainRepository): ViewModel {
        return DetailsViewModel(mainRepository)
    }

    // Add More ViewModels as needed.
}
