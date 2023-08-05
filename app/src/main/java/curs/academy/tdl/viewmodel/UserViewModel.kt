package curs.academy.tdl.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import curs.academy.domain.models.User
import curs.academy.domain.usecases.DeleteUserUseCase
import curs.academy.domain.usecases.GetUserIdUseCase
import curs.academy.domain.usecases.InsertUserUseCase
import curs.academy.domain.usecases.UpdateLoginUseCase
import curs.academy.domain.usecases.UpdatePasswordUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(
    private val insertUserUseCase: InsertUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateLoginUseCase: UpdateLoginUseCase,
    private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val getUserIdUseCase: GetUserIdUseCase
) : ViewModel(){

    private var _userId = -1
    val currentUserID = _userId

    fun setUserId(login: String, password: String) {
        viewModelScope.launch{
            _userId = getUserIdUseCase.execute(login,password)
        }
    }

    fun insertUser(login : String, password : String, userId :Int) : Boolean{
        if(isLoginValid(login) && isPasswordValid(password)) {
            viewModelScope.launch {
                insertUserUseCase.execute(User(userId, login, password))
            }
            return true
        }
        return false
    }

    fun deleteUser(id : Int){
        viewModelScope.launch {
            deleteUserUseCase.execute(id)
        }
    }

    fun updateLogin(newLogin : String, id: Int) : Boolean{
        if(isLoginValid(newLogin)){
            viewModelScope.launch {
                updateLoginUseCase.execute(newLogin, id)
            }
            return true
        }
        return false
    }

    fun updatePassword(newPassword : String, id : Int) : Boolean{
        if(isPasswordValid(newPassword)) {
            viewModelScope.launch {
                updatePasswordUseCase.execute(newPassword, id)
            }
            return true
        }
        return false
    }

    private fun isPasswordValid(password: String) : Boolean{
        if(password.isNotEmpty()){
            return true
        }
        return false
    }

    private fun isLoginValid(login: String) : Boolean{
        if(login.isNotEmpty()){
            return true
        }
        return false
    }
}

class UserViewModelFactory(private val insertUserUseCase: InsertUserUseCase,
                           private val deleteUserUseCase: DeleteUserUseCase,
                           private val updateLoginUseCase: UpdateLoginUseCase,
                           private val updatePasswordUseCase: UpdatePasswordUseCase,
                           private val getUserIdUseCase: GetUserIdUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(insertUserUseCase,
                                deleteUserUseCase,
                                updateLoginUseCase,
                                updatePasswordUseCase,
                                getUserIdUseCase) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel")
    }
}
