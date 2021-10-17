package com.clover.cloverricknmorty.ui.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.clover.cloverricknmorty.data.api.ApiService
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.util.Status
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class MainViewModelTest {

    @Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    var apiService: ApiService? = null


    @Mock
    //var mainRepository: MainRepository = MainRepository

//var apiClient: NewsApiClient? = null

    private var viewModel: MainViewModel? = null

    @Mock
    var observer: Observer<Status>? = null

    /*@Before
    fun setUp() {
    }*/
    @Before
    @Throws(Exception::class)
    open fun setUp() {
        MockitoAnnotations.initMocks(this)

    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCharacters() {
    }
}