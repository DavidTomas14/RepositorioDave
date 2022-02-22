package com.example.rxjava.datasources.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "USER")
data class UserDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int,
    val country: String
)
