package curs.academy.tdl.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import curs.academy.domain.models.AuthStatus
import curs.academy.domain.models.Task
import curs.academy.domain.models.User
import curs.academy.domain.models.UserCreads
import curs.academy.domain.usecases.auth.LoginUserFUseCase
import curs.academy.domain.usecases.auth.RegistrUserFUseCase
import curs.academy.domain.usecases.user.DeleteUserUseCase
import curs.academy.domain.usecases.user.GetAllUserUseCase
import curs.academy.domain.usecases.user.GetUserByIdUseCase
import curs.academy.domain.usecases.user.GetUserIdUseCase
import curs.academy.domain.usecases.user.InsertUserUseCase
import curs.academy.domain.usecases.user.UpdateLoginUseCase
import curs.academy.domain.usecases.user.UpdatePasswordUseCase
import curs.academy.tdl.model.ValidationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUserFUseCase: LoginUserFUseCase,
    private val registrUserFUseCase: RegistrUserFUseCase
) : ViewModel(){

    private var  _state : MutableLiveData<ValidationState> = MutableLiveData(ValidationState.DataValid)
    val state : LiveData<ValidationState> = _state

     suspend fun loginUser(login: String, password: String) : AuthStatus{
        if(isLoginValid(login) && isPasswordValid(password)){
            return viewModelScope.async(Dispatchers.IO) {
                loginUserFUseCase.execute(UserCreads(login, password))
            }.await()
        }
         return  AuthStatus(false,"login or password invalid","")
    }

    suspend fun registrUser(login: String, password: String) : AuthStatus{
        if(isLoginValid(login) && isPasswordValid(password)){
            return viewModelScope.async(Dispatchers.IO) {
                registrUserFUseCase.execute(UserCreads(login, password))
            }.await()
        }
        return  AuthStatus(false,"login or password invalid","")
    }

    private fun isLoginValid(login : String) : Boolean{
        if(login.length<8){
            _state  =  MutableLiveData(ValidationState.DataInvalid)
            return false
        }
        _state =  MutableLiveData(ValidationState.DataValid)
        return true
    }

    private fun isPasswordValid(password : String) : Boolean{
        if(password.length<4){
            _state =  MutableLiveData(ValidationState.DataInvalid)
            return false
        }
        _state =  MutableLiveData(ValidationState.DataValid)
        return true
    }

}

class AuthViewModelFactory( private val loginUserFUseCase: LoginUserFUseCase,
                            private val registrUserFUseCase: RegistrUserFUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AuthViewModel::class.java)){
            return AuthViewModel(loginUserFUseCase,
                                registrUserFUseCase) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel")
    }
}
