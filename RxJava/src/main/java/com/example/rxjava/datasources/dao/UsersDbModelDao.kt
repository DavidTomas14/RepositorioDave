package com.example.rxjava.datasources.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rxjava.datasources.constants.Constants.ELIMINAR_USER
import io.reactivex.Observable
import com.example.rxjava.datasources.constants.Constants.OBTENER_USERS
import com.example.rxjava.datasources.model.UserDbModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class UsersDbModelDao {

    @Query(OBTENER_USERS)
    abstract fun obtenerUsersSingle(): Single<List<UserDbModel>>

    @Query(OBTENER_USERS)
    abstract fun obtenerUsersObservable(): Observable<List<UserDbModel>>

    @Query("$ELIMINAR_USER where id =:id")
    abstract fun deleteUserById(id: Int): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertarUser(userDbModel: UserDbModel): Completable
}