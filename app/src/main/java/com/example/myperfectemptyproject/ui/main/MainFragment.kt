package com.example.myperfectemptyproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.myperfectemptyproject.R
import com.example.myperfectemptyproject.databinding.MainFragmentBinding
import com.example.myperfectemptyproject.di.injector
import com.example.myperfectemptyproject.utils.viewModel

class MainFragment : Fragment() {

    lateinit var binding : MainFragmentBinding
    private val viewmodel by viewModel {
        injector.mainViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}
