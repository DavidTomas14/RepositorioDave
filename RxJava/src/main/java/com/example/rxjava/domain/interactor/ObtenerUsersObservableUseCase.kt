package com.example.rxjava.domain.interactor

import com.example.rxjava.domain.executor.PostExecutionThread
import com.example.rxjava.domain.executor.ThreadExecutor
import com.example.rxjava.domain.repository.UserRepository
import com.example.rxjava.model.User
import com.hiberus.mobile.android.atenea.domain.interactor.ObservableUseCase
import io.reactivex.Observable

class ObtenerUsersObservableUseCase(
   private val userRepository: UserRepository,
   threadExecutor: ThreadExecutor,
   postExecutionThread: PostExecutionThread
    ) : ObservableUseCase<List<User>, Void?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Observable<List<User>> {
        return userRepository.obtenerUsersObservable()
    }

}