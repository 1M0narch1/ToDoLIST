package curs.academy.domain.usecases

import curs.academy.domain.repository.UserRepository

class UpdateLoginUseCase(private val userRepository: UserRepository) {

    fun execute(newLogin : String,id : Int){
        return userRepository.updateLogin(newLogin, id)
    }

}