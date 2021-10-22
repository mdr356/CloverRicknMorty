package com.clover.cloverricknmorty.ui.details.view

import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.clover.cloverricknmorty.databinding.FragmentDetailsBinding
import com.clover.cloverricknmorty.ui.base.BaseFragment
import com.clover.cloverricknmorty.ui.details.viewmodel.DetailsViewModel
import com.clover.cloverricknmorty.util.Status
import timber.log.Timber
import javax.inject.Inject

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    @Inject
    lateinit var viewModel: DetailsViewModel

    override fun getViewBinding(): FragmentDetailsBinding = FragmentDetailsBinding.inflate(layoutInflater)

    override fun observeData(refreshApiCall : Boolean, searchName: String) {

        arguments?.let {
            viewModel.getCharacterById(it.getInt("characterId")).observe(this , {
                characterList ->
                Glide.with(binding.image.context)
                    .load(characterList.image)
                    .into(binding.image)
                    //.onLoadStarted(resources.getDrawable(R.drawable.progress_bar))

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
}
