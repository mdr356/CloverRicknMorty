package com.clover.cloverricknmorty.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.clover.cloverricknmorty.data.api.ApiService
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.data.roomdatabase.CharacterDao
import com.clover.cloverricknmorty.ui.main.viewmodel.MainViewModel
import com.clover.cloverricknmorty.util.Resource
import com.clover.cloverricknmorty.util.Status
import kotlinx.coroutines.*
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService
    @Mock
    lateinit var observer: Observer<in Resource<List<CharacterList>?>>
    @Mock
    lateinit var mainRepository: MainRepository
    @Mock
    lateinit var characterList: List<CharacterList>
    @Mock
    lateinit var characterDao: CharacterDao

    private var viewModel: MainViewModel? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(mainRepository)
        viewModel?.getCharacters()?.observeForever(observer);
    }

    @Test
    fun testNull() = runBlocking {
        `when`(apiService.getCharacters()).thenReturn(null)
        val data = viewModel?.getCharacters(true)
        Assert.assertNotNull(data)
    }

    @Test
    fun testApiFetchDataSuccess() = runBlocking {
        `when`(characterDao.getCharacters()).thenReturn(arrayListOf())
        `when`(mainRepository.getCharacters()).thenReturn(characterList)
        verify(observer).onChanged(Resource(status = Status.SUCCESS, data = arrayListOf(), message = null))
    }
}
