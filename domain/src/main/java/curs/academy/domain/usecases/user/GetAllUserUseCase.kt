package curs.academy.domain.usecases.user

import curs.academy.domain.models.User
import curs.academy.domain.repository.UserRepository

class GetAllUserUseCase(private val userRepository: UserRepository) {

    suspend fun execute() : List<User> {
        return userRepository.getAllUser()
    }
}