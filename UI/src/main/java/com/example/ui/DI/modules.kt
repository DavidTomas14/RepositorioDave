package com.example.ui.DI

import android.widget.ArrayAdapter
import com.example.ui.R
import com.example.ui.Spinner.Coche
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module {

}

val spinnerModule = module {
    factory { ArrayAdapter<Coche>(androidContext(),R.layout.spinner_custom_item ) }
}