package com.example.firebase

import timber.log.Timber



interface CrashLibrary {

    fun log(priority: Int, tag: String?, message: String)

    fun logException(throwable: Throwable)

    fun setUser(userId: String)
}

