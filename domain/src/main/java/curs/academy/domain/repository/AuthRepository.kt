package curs.academy.domain.repository

import curs.academy.domain.models.AuthStatus
import curs.academy.domain.models.UserCreads

interface AuthRepository {
    suspend fun login(userCreads: UserCreads) :AuthStatus
    suspend fun registr(userCreads: UserCreads) :AuthStatus
}