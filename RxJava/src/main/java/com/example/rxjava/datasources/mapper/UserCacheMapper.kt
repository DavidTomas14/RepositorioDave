package com.example.rxjava.datasources.mapper

import com.example.rxjava.datasources.model.UserDbModel
import com.example.rxjava.model.User

class UserCacheMapper : EntityMapper<UserDbModel, User> {

    override fun mapFromCached(type: UserDbModel): User {
       return User(
           type.id,
           type.name,
           type.age,
           type.country
       )
    }

    override fun mapToCached(type: User): UserDbModel {
        return UserDbModel(
            type.id,
            type.name,
            type.age,
            type.country
        )
    }
}