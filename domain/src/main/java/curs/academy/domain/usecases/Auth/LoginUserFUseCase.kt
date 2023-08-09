package curs.academy.domain.usecases.Auth

import curs.academy.domain.models.AuthStatus
import curs.academy.domain.models.UserCreads
import curs.academy.domain.repository.AuthRepository

class LoginUserFUseCase(private val authRepository: AuthRepository) {

    suspend fun execute(userCreads: UserCreads) : AuthStatus{
        return authRepository.login(userCreads)
    }

}