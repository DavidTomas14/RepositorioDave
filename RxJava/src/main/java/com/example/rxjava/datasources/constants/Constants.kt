package com.example.rxjava.datasources.constants

object Constants {
    const val USER_TABLE = "USER"

    const val OBTENER_USERS = "SELECT * FROM $USER_TABLE"

    const val ELIMINAR_USER = "DELETE FROM $USER_TABLE"
}