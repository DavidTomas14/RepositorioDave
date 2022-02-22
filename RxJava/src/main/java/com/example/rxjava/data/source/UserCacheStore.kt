package com.example.rxjava.data.source

import com.example.rxjava.model.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface UserCacheStore {

    fun obtenerUsersSingle(): Single<List<User>>
    fun obtenerUsersObservable(): Observable<List<User>>

    fun deleteUserById(id: Int): Completable

    fun insertarUser(user: User): Completable

}