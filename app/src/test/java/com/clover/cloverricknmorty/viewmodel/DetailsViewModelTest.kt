package com.clover.cloverricknmorty.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.clover.cloverricknmorty.data.api.ApiService
import com.clover.cloverricknmorty.data.model.CharacterLocation
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.ui.details.viewmodel.DetailsViewModel
import com.clover.cloverricknmorty.util.Resource
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
    lateinit var apiService: ApiService
    @Mock
    lateinit var observer: Observer<in Resource<CharacterLocation?>>
    @Mock
    lateinit var mainRepository: MainRepository
    @Mock
    lateinit var context: Application

    var characterLocation = CharacterLocation(1,"","","", arrayListOf(),"","")

    private var viewModel: DetailsViewModel? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailsViewModel(mainRepository, context)
        viewModel?.getCharacterLocation("1")?.observeForever(observer);
    }

    @Test
    fun testNull() = runBlocking {
        `when`(apiService.getCharacterLocation("1")).thenReturn(null)
        val data = viewModel?.getCharacterLocation("1")
        Assert.assertNotNull(data)
    }
}
