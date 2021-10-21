package com.clover.cloverricknmorty.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.clover.cloverricknmorty.data.api.ApiService
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.data.roomdatabase.CharacterDao
import com.clover.cloverricknmorty.ui.main.viewmodel.MainViewModel
import com.clover.cloverricknmorty.util.Resource
import com.clover.cloverricknmorty.util.Status
import io.mockk.*
import kotlinx.coroutines.*
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mainRepository: MainRepository
    @Mock
    lateinit var characterList: List<CharacterList>
    @Mock
    lateinit var characterDao: CharacterDao

    private var viewModel: MainViewModel? = null

    val observer = mockk<Observer<Any>>(relaxed = true)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(mainRepository)
    }

    @Test
    fun testApiFetchDataError() = runBlocking {
        `when`(characterDao.getCharacters()).thenReturn(null)
        `when`(mainRepository.getCharacters()).thenReturn(null)
        viewModel?.getCharacters(true)?.observeForever(observer)
        verifySequence {
            observer.onChanged(Resource(status= Status.LOADING, data=null, message=null))
            observer.onChanged(Resource(status= Status.ERROR, data=null, message="Error calling getCharacter() api"))
        }
    }

    // this test fails intermittently.
    @Test
    fun testApiFetchDataSuccess() = runBlocking {
        `when`(characterDao.getCharacters()).thenReturn(arrayListOf())
        `when`(mainRepository.getCharacters()).thenReturn(characterList)
        viewModel?.getCharacters(true)?.observeForever(observer)
        verifySequence {
            observer.onChanged(Resource(status= Status.LOADING, data=null, message=null))
            observer.onChanged(Resource(status= Status.SUCCESS, data=characterList, message=null))
        }
    }

    @Test
    fun testDatabaseSuccess() = runBlocking {
        `when`(characterDao.getCharacters()).thenReturn(arrayListOf())
        `when`(mainRepository.loadCharactersFromDatabase()).thenReturn(characterList)
        viewModel?.getCharacters(false)?.observeForever(observer)
        verifySequence {
            observer.onChanged(Resource(status= Status.LOADING, data=null, message=null))
            observer.onChanged(Resource(status= Status.SUCCESS, data=characterList, message=null))
        }
    }

    @Test
    fun testDatabaseError() = runBlocking {
        `when`(mainRepository.loadCharactersFromDatabase()).thenReturn(null)
        viewModel?.getCharacters(false)?.observeForever(observer)
        verifySequence {
            observer.onChanged(Resource(status= Status.LOADING, data=null, message=null))
            observer.onChanged(Resource(status= Status.ERROR, data=null, message="Error loading data"))
        }
    }
}

