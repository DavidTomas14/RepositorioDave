package com.example.rxjava.data.repository

import com.example.rxjava.data.source.UserDataStoreFactory
import com.example.rxjava.domain.repository.UserRepository
import com.example.rxjava.model.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class UserDataRepository(
   private val factory: UserDataStoreFactory
): UserRepository {

    override fun obtenerUsersSingle(): Single<List<User>> {
        return factory.retrieveCacheDataStore().obtenerUsersSingle()
    }

    override fun obtenerUsersObservable(): Observable<List<User>> {
        return factory.retrieveCacheDataStore().obtenerUsersObservable()
    }

    override fun deleteUserById(id: Int): Completable {
        return factory.retrieveCacheDataStore().deleteUserById(id)
    }

    override fun insertarUser(user: User): Completable {
        return factory.retrieveCacheDataStore().insertarUser(user)
    }
}