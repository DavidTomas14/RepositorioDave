package com.example.ui.DI

import android.widget.ArrayAdapter
import com.example.navigation.NavGraphFragmentsWithMainNavViewModel.viewmodel.MainNavigationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    viewModel { MainNavigationViewModel() }
}
