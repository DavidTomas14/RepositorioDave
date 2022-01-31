package com.example.firebase

import timber.log.Timber



object TimberLogImplementation : TimberLog {

    override fun init() {
        init("")
    }

    override fun init(userId: String) {
        Timber.plant(CrashLibraryTree(userId))
    }
}
