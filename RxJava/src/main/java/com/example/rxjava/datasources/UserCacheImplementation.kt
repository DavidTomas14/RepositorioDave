package com.example.rxjava.datasources

import com.example.rxjava.data.source.UserCacheStore
import com.example.rxjava.datasources.db.RepositorioDatabase
import com.example.rxjava.datasources.mapper.UserCacheMapper
import com.example.rxjava.model.User
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class UserCacheImplementation constructor(
    private val repositorioDatabase: RepositorioDatabase,
    private val userCacheMapper: UserCacheMapper
): UserCacheStore {

    override fun obtenerUsersSingle(): Single<List<User>> {
        return repositorioDatabase.userDbModelDao().obtenerUsersSingle().map {
            it.map { userDbModel->
                userCacheMapper.mapFromCached(userDbModel)
            }
        }
    }

    override fun obtenerUsersObservable(): Observable<List<User>> {
        return repositorioDatabase.userDbModelDao().obtenerUsersObservable().map {
            it.map { userDbModel->
                userCacheMapper.mapFromCached(userDbModel)
            }
        }
    }

    override fun deleteUserById(id: Int): Completable {
        return repositorioDatabase.userDbModelDao().deleteUserById(id)
    }

    override fun insertarUser(user: User): Completable {
        return repositorioDatabase.userDbModelDao().insertarUser(userCacheMapper.mapToCached(user))
    }

}