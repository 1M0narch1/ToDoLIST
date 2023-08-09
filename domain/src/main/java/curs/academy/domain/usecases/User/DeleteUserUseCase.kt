package curs.academy.domain.usecases.User

import curs.academy.domain.repository.UserRepository

class DeleteUserUseCase(private val userRepository: UserRepository) {

    fun execute(id : Int){
        return userRepository.deleteUser(id)
    }

}