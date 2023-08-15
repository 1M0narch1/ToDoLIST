package curs.academy.domain.usecases.auth

import curs.academy.domain.models.AuthStatus
import curs.academy.domain.models.UserCreads
import curs.academy.domain.repository.AuthRepository

class RegistrUserFUseCase(private val authRepository: AuthRepository) {

    suspend fun execute(userCreads: UserCreads) : AuthStatus{
        return authRepository.registr(userCreads)
    }
}