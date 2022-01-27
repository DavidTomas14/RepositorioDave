package com.example.navigation.NavGraphFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigation.R
import com.example.navigation.Utils.BaseFragment
import com.example.navigation.databinding.FragmentFragment1Binding
import timber.log.Timber

class Fragment2 : BaseFragment() {

    private lateinit var binding: FragmentFragment1Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFragment1Binding.inflate(layoutInflater)
        Timber.i("Navigation: Fragment2")
        return binding.root
    }

    override fun initializeContents(savedInstanceState: Bundle?) {
        super.initializeContents(savedInstanceState)
    }

    override fun initializeViews(savedInstanceState: Bundle?) {
        super.initializeViews(savedInstanceState)
        binding.tvFragmento.text = "Fragmento 2"
        binding.btnNavegar.setOnClickListener {
            findNavController().navigate(R.id.fragment3)
        }
    }
}