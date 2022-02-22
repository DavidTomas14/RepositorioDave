package com.example.rxjava.domain.repository

import com.example.rxjava.model.User
import io.reactivex.*

interface UserRepository {

    fun obtenerUsersSingle(): Single<List<User>>
    fun obtenerUsersObservable(): Observable<List<User>>

    fun deleteUserById(id: Int): Completable

    fun insertarUser(user: User): Completable

}