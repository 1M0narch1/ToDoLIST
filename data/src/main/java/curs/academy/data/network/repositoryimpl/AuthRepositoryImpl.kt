package curs.academy.data.network.repositoryimpl

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import curs.academy.domain.models.AuthStatus
import curs.academy.domain.models.User
import curs.academy.domain.models.UserCreads
import curs.academy.domain.repository.AuthRepository
import curs.academy.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private var auth: FirebaseAuth,
    ):AuthRepository {

    override suspend fun login(userCreads: UserCreads) : AuthStatus {
        return try {
            var status = false
            var message = ""
            val result = auth.signInWithEmailAndPassword(
                userCreads.email,
                userCreads.password
            ).addOnCompleteListener {task->
                if(task.isSuccessful){
                status = true
                }
            }.addOnFailureListener{
                message = "log in failure"
            }.addOnCanceledListener {
                    message = "log in canceled"
            }.addOnSuccessListener {
                status = true
            }.await()
            AuthStatus(status, message, result.user?.uid ?: "lll")
        }
        catch (e:Exception){
            AuthStatus(false,e.message ?: "an incomprehensible error", "")
        }
    }

    override suspend fun registr(userCreads: UserCreads) : AuthStatus {
        return try {
            var status = false
            var message = ""
            val result = auth.createUserWithEmailAndPassword(
                userCreads.email,
                userCreads.password
            ).addOnCompleteListener {
                status = true
            }.addOnFailureListener{
                message = "registration failure"
            }.addOnCanceledListener {
                message = "registration canceled"
            }.await()
            AuthStatus(status, message, result.user?.uid ?: "lll")
        }
        catch (e:Exception){
            AuthStatus(false,e.message ?: "an incomprehensible error","")
        }
    }

}