package com.example.rxjava.datasources.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rxjava.datasources.dao.UsersDbModelDao
import com.example.rxjava.datasources.model.UserDbModel

@Database(
    entities = [
        UserDbModel::class
    ],
    version = 1,
    exportSchema = true
)
abstract class RepositorioDatabase: RoomDatabase() {

    abstract fun userDbModelDao(): UsersDbModelDao
}