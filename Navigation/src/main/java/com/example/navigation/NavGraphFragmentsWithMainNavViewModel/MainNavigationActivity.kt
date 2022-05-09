package com.example.navigation.NavGraphFragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.navigation.NavGraphFragmentsWithMainNavViewModel.util.MainNavigationCommand
import com.example.navigation.NavGraphFragmentsWithMainNavViewModel.viewmodel.MainNavigationViewModel
import com.example.navigation.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainNavigationActivity : AppCompatActivity() {

    private val mainNavigationViewModel: MainNavigationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_with_nav_viewmodel)

        mainNavigationViewModel.getNavigationCommandEvent().observe(this){command->
            when(command){
                MainNavigationCommand.GoToFragment9->{
                    findNavController(R.id.navHostFragment).navigate(R.id.fragment9)
                }
                MainNavigationCommand.GoToFragment10->{
                    findNavController(R.id.navHostFragment).navigate(R.id.fragment10)
                }
                MainNavigationCommand.GoToFragment11->{
                    findNavController(R.id.navHostFragment).navigate(R.id.fragment11)
                }
            }
        }
    }
}