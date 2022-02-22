package com.example.rxjava.app.di

import androidx.room.Room
import com.example.rxjava.app.UiThread
import com.example.rxjava.app.UserViewModel
import com.example.rxjava.data.repository.UserDataRepository
import com.example.rxjava.data.source.UserCacheStore
import com.example.rxjava.data.source.UserDataStoreFactory
import com.example.rxjava.datasources.UserCacheImplementation
import com.example.rxjava.datasources.db.RepositorioDatabase
import com.example.rxjava.datasources.mapper.UserCacheMapper
import com.example.rxjava.domain.repository.UserRepository
import com.example.rxjava.domain.executor.JobExecutor
import com.example.rxjava.domain.executor.PostExecutionThread
import com.example.rxjava.domain.executor.ThreadExecutor
import com.example.rxjava.domain.interactor.CreateUserUseCase
import com.example.rxjava.domain.interactor.DeleteUserByIdUseCase
import com.example.rxjava.domain.interactor.ObtenerUsersObservableUseCase
import com.example.rxjava.domain.interactor.ObtenerUsersSingleUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {

    single<ThreadExecutor> { JobExecutor() }
    single<PostExecutionThread> { UiThread() }

    single {
        Room.databaseBuilder(
            androidContext(),
            RepositorioDatabase::class.java,
            "repositorio_dave.sqlite"
        ).build()
    }
}

val userModule = module {
    factory { ObtenerUsersObservableUseCase(get(), get(), get()) }
    factory { ObtenerUsersSingleUseCase(get(), get(), get()) }
    factory { DeleteUserByIdUseCase(get(), get(), get()) }
    factory { CreateUserUseCase(get(), get(), get()) }
    viewModel { UserViewModel(get(), get(), get(), get()) }
    factory { UserCacheMapper() }
    factory<UserCacheStore> { UserCacheImplementation(get(), get()) }
    factory { UserDataStoreFactory(get()) }
    factory<UserRepository> { UserDataRepository(get()) }

}