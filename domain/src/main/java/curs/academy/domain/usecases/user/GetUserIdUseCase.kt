package curs.academy.domain.usecases.user

import curs.academy.domain.repository.UserRepository

class GetUserIdUseCase(private val userRepository: UserRepository) {

    suspend fun execute(login : String, password : String) : String{
        return userRepository.getUserId(login, password)
    }

}