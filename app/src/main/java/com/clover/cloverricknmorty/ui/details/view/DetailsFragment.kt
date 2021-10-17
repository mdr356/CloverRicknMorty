package com.clover.cloverricknmorty.ui.details.view

import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.clover.cloverricknmorty.R
import com.clover.cloverricknmorty.data.api.RetrofitBuilder
import com.clover.cloverricknmorty.databinding.FragmentDetailsBinding
import com.clover.cloverricknmorty.ui.base.BaseFragment
import com.clover.cloverricknmorty.app.MyApplication
import com.clover.cloverricknmorty.ui.base.ViewModelFactory
import com.clover.cloverricknmorty.ui.details.viewmodel.DetailsViewModel
import com.clover.cloverricknmorty.util.Status
import timber.log.Timber

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    private lateinit var viewModel: DetailsViewModel

    override fun getViewBinding(): FragmentDetailsBinding = FragmentDetailsBinding.inflate(layoutInflater)

    override fun observeData(refreshApiCall : Boolean) {

        arguments?.let {
            viewModel.getCharacterById(it.getInt("characterId")).observe(this , {
                characterList ->
                Glide.with(binding.image.context)
                    .load(characterList.image)
                    .into(binding.image)
                    .onLoadStarted(resources.getDrawable(R.drawable.progress_bar))

                // API Call to get Location
                characterList.location?.url?.let { url ->
                    viewModel.getCharacterLocation(url).observe(this, {
                        resource ->
                        when(resource.status) {
                            Status.SUCCESS -> {
                                Timber.d("Request Success")
                                binding.location.text = resource.data?.name
                                binding.progressBar.visibility = View.GONE
                                binding.dimension.text = resource.data?.dimension
                                binding.type.text = resource.data?.type
                                binding.residents.text = resource.data?.residents?.size?.toString()
                            }
                            Status.LOADING -> {
                                Timber.d("Request Loading")
                                binding.progressBar.visibility = View.VISIBLE
                            }
                            Status.ERROR -> {
                                Timber.d("Request Error")
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(activity, resource.message, Toast.LENGTH_LONG).show()
                            }
                        }
                    })
                }
            })
        }
    }
    override fun setUpViewModel() {
        super.setUpViewModel()
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(apiHelper = ApiHelper(RetrofitBuilder.apiService), applicatinContext = activity?.application as MyApplication)
        ).get(DetailsViewModel::class.java)
    }
}
