package com.clover.cloverricknmorty.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/*
 * BaseFragment accepts ViewBinding
 */
abstract class BaseFragment<VBinding : ViewBinding> : DaggerFragment() {

    lateinit var binding: VBinding
    // getView Binding
    protected abstract fun getViewBinding(): VBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData(false)
    }

    // default optional empty implementation for views
    open fun setUpViews() {}
    open fun observeView() {}
    open fun observeData(refreshApiCall : Boolean, searchName: String="") {}
    open fun setUpViewModel(){}

    private fun init() {
        binding = getViewBinding() // inflate view
        setUpViewModel() // setup view model
     }
}