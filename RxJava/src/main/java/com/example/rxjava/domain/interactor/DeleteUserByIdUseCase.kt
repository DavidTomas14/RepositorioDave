package com.example.rxjava.domain.interactor

import com.example.rxjava.domain.executor.PostExecutionThread
import com.example.rxjava.domain.executor.ThreadExecutor
import com.example.rxjava.domain.interactor.baseInteractor.CompletableUseCase
import com.example.rxjava.domain.repository.UserRepository
import io.reactivex.Completable

class DeleteUserByIdUseCase(
    private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
):CompletableUseCase<Int>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Int?): Completable {
        checkNotNull(params)
        return userRepository.deleteUserById(params)
    }
}