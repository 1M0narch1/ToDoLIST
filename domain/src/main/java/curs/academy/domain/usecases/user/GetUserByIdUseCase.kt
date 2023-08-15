package curs.academy.domain.usecases.user

import curs.academy.domain.models.User
import curs.academy.domain.repository.UserRepository

class GetUserByIdUseCase(private val userRepository: UserRepository) {

    suspend fun execute(userId : String) : User{
        return userRepository.getUserByUserId(userId)
    }
}