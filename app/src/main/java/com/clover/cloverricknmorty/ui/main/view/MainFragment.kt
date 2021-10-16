package com.clover.cloverricknmorty.ui.main.view

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.clover.cloverricknmorty.data.api.ApiHelper
import com.clover.cloverricknmorty.data.api.RetrofitBuilder
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.databinding.FragmentMainBinding
import com.clover.cloverricknmorty.ui.base.BaseFragment
import com.clover.cloverricknmorty.ui.base.ViewModelFactory
import com.clover.cloverricknmorty.ui.main.adapter.MainAdapter
import com.clover.cloverricknmorty.ui.main.viewmodel.MainViewModel
import com.clover.cloverricknmorty.util.Status
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel

    // todo(): add this to base class.
    override fun setUpViewModel() {
        super.setUpViewModel()
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    override fun getViewBinding() = FragmentMainBinding.inflate(layoutInflater)

    override fun setUpViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = MainAdapter(arrayListOf())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    override fun observeData() {
        super.observeData()
        viewModel.getCharacters().observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Timber.d("Request Success")
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { it -> addList(it.results) }
                    }
                    Status.ERROR -> {
                        Timber.d("Request Error")
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Timber.d("Request Loading")
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun addList(charaterList: List<CharacterList>) {
        Timber.d("Update RecyclerView with the list of characters")
        adapter.apply {
            addCharacters(charaterList)
            notifyDataSetChanged()
        }
    }

        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
}