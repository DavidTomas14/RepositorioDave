package com.example.navigation.NavGraphFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigation.R
import com.example.navigation.Utils.BaseFragment
import com.example.navigation.databinding.FragmentFragment1Binding
import timber.log.Timber

class Fragment4 : BaseFragment() {

    private lateinit var binding: FragmentFragment1Binding
    private val args: Fragment4Args by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFragment1Binding.inflate(layoutInflater)
        Timber.i("Navigation: Fragment4")
        return binding.root
    }

    override fun initializeContents(savedInstanceState: Bundle?) {
        super.initializeContents(savedInstanceState)
    }

    override fun initializeViews(savedInstanceState: Bundle?) {
        super.initializeViews(savedInstanceState)
        binding.tvFragmento.text = "Fragmento 4 + ${args.argumento}"


        binding.btnNavegar.setOnClickListener {

            //Opcion A
            findNavController().navigate(R.id.fragment2)

            //Opcion B
            //findNavController().popBackStack(R.id.fragment2, false)

        }
    }
}