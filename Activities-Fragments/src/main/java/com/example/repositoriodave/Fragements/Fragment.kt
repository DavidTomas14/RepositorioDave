package com.example.repositoriodave.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.repositoriodave.Fragements.BaseFragment
import com.example.repositoriodave.databinding.FragmentBasicBinding

class Fragment : BaseFragment() {

    private lateinit var binding: FragmentBasicBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentBasicBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun initializeContents(savedInstanceState: Bundle?) {
        super.initializeContents(savedInstanceState)
    }

    override fun initializeState(savedInstanceState: Bundle?) {
        super.initializeState(savedInstanceState)

        //Inicializar observadores
    }

    override fun initializeViews(savedInstanceState: Bundle?) {
        super.initializeViews(savedInstanceState)

        //Inicializar lo asociado a las vistas.
    }
}