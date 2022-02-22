package com.example.rxjava.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjava.domain.interactor.CreateUserUseCase
import com.example.rxjava.domain.interactor.DeleteUserByIdUseCase
import com.example.rxjava.domain.interactor.ObtenerUsersObservableUseCase
import com.example.rxjava.domain.interactor.ObtenerUsersSingleUseCase
import com.example.rxjava.model.User
import com.example.rxjava.util.Event
import com.example.rxjava.util.ResourceState
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

typealias UserItemState = ResourceState<List<User>>

class UserViewModel(
    private val obtenerUsersObservableUseCase: ObtenerUsersObservableUseCase,
    private val obtenerUsersSingleUseCase: ObtenerUsersSingleUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase,
    private val crearUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    //Users from RoomDatabase
    private val usertemListMutableLiveData: MutableLiveData<UserItemState> = MutableLiveData()
    fun obtenerUsers(): LiveData<UserItemState> {
        return usertemListMutableLiveData
    }

    fun fetchUsersSingle() {
        usertemListMutableLiveData.value = ResourceState.Loading()
        compositeDisposable.add(
            obtenerUsersSingleUseCase.execute().subscribe(
                {
                    usertemListMutableLiveData.value = ResourceState.Success(it)
                },
                {
                    usertemListMutableLiveData.value = ResourceState.Error(it.toString())
                    Timber.e(it)
                },
            )
        )
    }

    fun fetchUsersObservable() {
        usertemListMutableLiveData.value = ResourceState.Loading()
        compositeDisposable.add(obtenerUsersObservableUseCase.execute().subscribe(
            {
                usertemListMutableLiveData.value = ResourceState.Success(it)
            },
            {
                usertemListMutableLiveData.value = ResourceState.Error(it.toString())
                Timber.e(it)
            },
            {
                //OnComplete
            }
        ))
    }

    //UserEvent para mostrar un Toast
    private val userEvent = MutableLiveData<Event<String>>()
    fun obtenerUser(): LiveData<Event<String>> {
        return userEvent
    }

    fun setUserName() {
        userEvent.value = Event("David")
    }

    //DeleteUser
    private val userCommand = MutableLiveData<Event<UserCommand>>()
    fun obtenerUserCommand(): LiveData<Event<UserCommand>> {
        return userCommand
    }

    fun deleteUserById() {
        compositeDisposable.add(
            deleteUserByIdUseCase.execute(2).subscribe(
                {
                    userCommand.value = Event(UserCommand.CompletedDeleteUserCommand)
                },
                {
                    userCommand.value = Event(UserCommand.ErrorDeleteUserCommand)
                    Timber.d(it)
                },)
        )
    }

    fun crearUser(){
        compositeDisposable.add(
            crearUserUseCase.execute(
                User(0, "David", 23, "Spain")
            ).subscribe(
                {
                    userCommand.value = Event(UserCommand.CompletedCreadoUserCommand)
                },
                {
                    userCommand.value = Event(UserCommand.ErrorCreadoUserCommand)
                    Timber.d(it)
                }
            )
        )
    }


}