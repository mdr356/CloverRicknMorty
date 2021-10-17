package com.clover.cloverricknmorty.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.clover.cloverricknmorty.R
import com.clover.cloverricknmorty.data.model.CharacterList
import com.clover.cloverricknmorty.databinding.FragmentMainBinding
import com.clover.cloverricknmorty.ui.base.BaseFragment
import com.clover.cloverricknmorty.ui.main.adapter.MainAdapter
import com.clover.cloverricknmorty.ui.main.adapter.OnItemClickListener
import com.clover.cloverricknmorty.ui.main.viewmodel.MainViewModel
import com.clover.cloverricknmorty.util.Status
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : BaseFragment<FragmentMainBinding>(), OnItemClickListener {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var adapter: MainAdapter

    override fun getViewBinding() = FragmentMainBinding.inflate(layoutInflater)

    override fun setUpViews() {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = MainAdapter(arrayListOf(), this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            observeData(true)
        }
    }

    override fun observeData(refreshApiCall : Boolean) {
        super.observeData(refreshApiCall)
        viewModel.getCharacters(refreshApiCall).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Timber.d("Request Success")
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        resource.data?.let { it -> addList(it) }
                        binding.swipeRefreshLayout.isRefreshing = false
                    }
                    Status.ERROR -> {
                        Timber.d("Request Error")
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.swipeRefreshLayout.isRefreshing = false
                        Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        Timber.d("Request Loading")
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                        binding.swipeRefreshLayout.isRefreshing = false
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

    override fun onItemClicked(characterId: Int) {
        findNavController().navigate(
            R.id.action_MainFragment_to_DetailsFragment,
            Bundle().apply { putInt("characterId", characterId) })
    }
}
