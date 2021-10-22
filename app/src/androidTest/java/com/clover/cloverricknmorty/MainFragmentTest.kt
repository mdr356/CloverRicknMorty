package com.clover.cloverricknmorty

import androidx.core.view.isVisible
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.clover.cloverricknmorty.data.repository.MainRepository
import com.clover.cloverricknmorty.data.roomdatabase.CharacterDao
import com.clover.cloverricknmorty.ui.main.view.MainFragment
import com.clover.cloverricknmorty.ui.main.viewmodel.MainViewModel
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    var mainRepository: MainRepository = mockk()
    var characterDao: CharacterDao  = mockk()

    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(mainRepository)
    }

    @Test
    fun assertLoadingScreenIsShown() {
        val scenario = launchFragmentInContainer<MainFragment>()
        scenario.onFragment { fragment ->
            fragment.viewModel = viewModel
            Assert.assertTrue(fragment.binding.progressBar.isVisible)
        }
    }
    // add okhttp MockWebServer


}