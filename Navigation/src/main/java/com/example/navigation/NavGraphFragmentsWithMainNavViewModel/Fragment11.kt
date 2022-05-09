package com.example.navigation.NavGraphFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigation.NavGraphFragmentsWithMainNavViewModel.util.MainNavigationCommand
import com.example.navigation.NavGraphFragmentsWithMainNavViewModel.viewmodel.MainNavigationViewModel
import com.example.navigation.Utils.BaseFragment
import com.example.navigation.databinding.FragmentFragment1Binding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class Fragment11 : BaseFragment() {

    private lateinit var binding: FragmentFragment1Binding
    private val navigationViewModel: MainNavigationViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFragment1Binding.inflate(layoutInflater)
        Timber.i("Navigation: Fragment3")
        return binding.root
    }

    override fun initializeContents(savedInstanceState: Bundle?) {
        super.initializeContents(savedInstanceState)
    }

    override fun initializeViews(savedInstanceState: Bundle?) {
        super.initializeViews(savedInstanceState)
        binding.tvFragmento.text = "Fragmento 11"
        binding.btnNavegar.setOnClickListener {
            navigationViewModel.send(MainNavigationCommand.GoToFragment9)
        }
    }
}