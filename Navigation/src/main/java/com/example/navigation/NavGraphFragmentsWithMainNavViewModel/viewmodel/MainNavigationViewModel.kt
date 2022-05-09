package com.example.navigation.NavGraphFragmentsWithMainNavViewModel.viewmodel

import androidx.lifecycle.ViewModel
import com.example.navigation.NavGraphFragmentsWithMainNavViewModel.util.MainNavigationCommand
import com.example.navigation.NavGraphFragmentsWithMainNavViewModel.util.SingleLiveEvent

class MainNavigationViewModel():ViewModel(){

    private val mainNavigationCommandEvent: SingleLiveEvent<MainNavigationCommand> = SingleLiveEvent()

    fun getNavigationCommandEvent(): SingleLiveEvent<MainNavigationCommand> = mainNavigationCommandEvent

    fun send(mainNavigationCommand: MainNavigationCommand){
        mainNavigationCommandEvent.value = mainNavigationCommand
    }

}