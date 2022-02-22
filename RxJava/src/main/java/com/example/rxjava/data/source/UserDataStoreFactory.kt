package com.example.rxjava.data.source

class UserDataStoreFactory(
    private val userCacheDataStore: UserCacheStore
    ) {

    fun retrieveCacheDataStore(): UserCacheStore{
        return userCacheDataStore
    }
}