package com.clover.cloverricknmorty.ui.details.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.clover.cloverricknmorty.R
import com.clover.cloverricknmorty.databinding.FragmentDetailsBinding
import com.clover.cloverricknmorty.ui.base.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    /* binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }*/


    override fun getViewBinding(): FragmentDetailsBinding = FragmentDetailsBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()

    }
}