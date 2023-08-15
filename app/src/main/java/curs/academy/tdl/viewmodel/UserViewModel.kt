package curs.academy.tdl.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import curs.academy.domain.models.User
import curs.academy.domain.usecases.user.DeleteUserUseCase
import curs.academy.domain.usecases.user.GetAllUserUseCase
import curs.academy.domain.usecases.user.GetUserByIdUseCase
import curs.academy.domain.usecases.user.GetUserIdUseCase
import curs.academy.domain.usecases.user.InsertUserUseCase
import curs.academy.domain.usecases.user.UpdateLoginUseCase
import curs.academy.domain.usecases.user.UpdatePasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(
    private val insertUserUseCase: InsertUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val updateLoginUseCase: UpdateLoginUseCase,
    private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val getUserIdUseCase: GetUserIdUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getAllUserUseCase: GetAllUserUseCase
) : ViewModel(){

    var currentUserID = ""

    suspend fun setUserId(login: String, password: String) {
        viewModelScope.async{
            currentUserID = getUserIdUseCase.execute(login,password)
        }.await()
    }

    suspend fun getAllUser() : List<User>{
        return viewModelScope.async {
            getAllUserUseCase.execute()
        }.await()
    }

    suspend fun getUserById(userId : String) : User{
        return viewModelScope.async {
            getUserByIdUseCase.execute(userId)
        }.await()
    }

    fun insertUser(login : String, password : String, userId :String) : Boolean{
        if(isLoginValid(login) && isPasswordValid(password)) {
            viewModelScope.launch(Dispatchers.IO) {
                insertUserUseCase.execute(User(userId, login, password))
                currentUserID = userId
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

    fun updatePassword(newPassword : String, id: Int) : Boolean{
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
                           private val getUserIdUseCase: GetUserIdUseCase,
                           private val getUserByIdUseCase: GetUserByIdUseCase,
                           private val getAllUserUseCase: GetAllUserUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UserViewModel::class.java)){
            return UserViewModel(insertUserUseCase,
                                deleteUserUseCase,
                                updateLoginUseCase,
                                updatePasswordUseCase,
                                getUserIdUseCase,
                                getUserByIdUseCase,
                                getAllUserUseCase) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel")
    }
}
