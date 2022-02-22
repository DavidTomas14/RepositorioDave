package com.example.rxjava.domain.interactor

import com.example.rxjava.domain.executor.PostExecutionThread
import com.example.rxjava.domain.executor.ThreadExecutor
import com.example.rxjava.domain.repository.UserRepository
import com.example.rxjava.model.User
import com.example.rxjava.util.baseInteractor.SingleUseCase
import io.reactivex.Single

class ObtenerUsersSingleUseCase(
   private val userRepository: UserRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
    ) : SingleUseCase<List<User>, Void?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Single<List<User>> {
        return userRepository.obtenerUsersSingle()
    }

}