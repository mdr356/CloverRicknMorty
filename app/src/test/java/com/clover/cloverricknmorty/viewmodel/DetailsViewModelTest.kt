package com.clover.cloverricknmorty.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.clover.cloverricknmorty.data.api.ApiService
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.data.model.CharacterLocation
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.data.roomdatabase.CharacterDao
import com.clover.cloverricknmorty.ui.details.viewmodel.DetailsViewModel
import com.clover.cloverricknmorty.ui.main.viewmodel.MainViewModel
import com.clover.cloverricknmorty.util.Resource
import com.clover.cloverricknmorty.util.Status
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class DetailsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mainRepository: MainRepository

    var characterLocation = CharacterLocation(1,"","","", arrayListOf(""),"","")

    private var viewModel: DetailsViewModel? = null

    val observer = mockk<Observer<Any>>(relaxed = true)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailsViewModel(mainRepository)
    }

    @Test
    fun testApiFetchDataError() = runBlocking {
        `when`(mainRepository.getCharacterLocation("1")).thenReturn(null)
        viewModel?.getCharacterLocation("location/1")?.observeForever(observer)
        verifySequence {
            observer.onChanged(Resource(status= Status.LOADING, data=null, message=null))
            observer.onChanged(Resource(status= Status.ERROR, data=null, message="Error calling getting Characters locaction api"))
        }
    }

    @Test
    fun testApiFetchDataSuccess() = runBlocking {
        `when`(mainRepository.getCharacterLocation("1")).thenReturn(characterLocation)
        viewModel?.getCharacterLocation("location/1")?.observeForever(observer)
        verifySequence {
            observer.onChanged(Resource(status= Status.LOADING, data=null, message=null))
            observer.onChanged(Resource(status= Status.SUCCESS, data=characterLocation, message=null))
        }
    }
}
