package com.example.rxjava.domain.interactor

import com.example.rxjava.domain.executor.PostExecutionThread
import com.example.rxjava.domain.executor.ThreadExecutor
import com.example.rxjava.domain.interactor.baseInteractor.CompletableUseCase
import com.example.rxjava.domain.repository.UserRepository
import com.example.rxjava.model.User
import com.hiberus.mobile.android.atenea.domain.interactor.ObservableUseCase
import io.reactivex.Completable
import io.reactivex.Observable

class CreateUserUseCase(
    private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
):CompletableUseCase<User>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: User?): Completable {
        checkNotNull(params)
        return userRepository.insertarUser(params)
    }
}