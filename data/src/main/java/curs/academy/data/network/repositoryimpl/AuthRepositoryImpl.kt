package curs.academy.data.network.repositoryimpl

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
    private val userRepository: UserRepository
    ):AuthRepository {

    private val job = SupervisorJob()
    private val authScope = CoroutineScope(job + Dispatchers.IO)

    override suspend fun login(userCreads: UserCreads) : AuthStatus {
        return try {
            var status = false
            var message = ""
            val result = auth.signInWithEmailAndPassword(
                userCreads.email,
                userCreads.password
            ).addOnCompleteListener {
                status = true
            }.addOnFailureListener{
                message = "log in failure"
            }.addOnCanceledListener {
                    message = "log in canceled"
            }.await()
            authScope.launch {
                userRepository.insertUser(
                    User(
                        result.user?.uid!!.toInt(),
                        userCreads.email,
                        userCreads.password
                    )
                )
            }
            AuthStatus(status, message, result.user?.uid!!.toInt())
        }
        catch (e:Exception){
            AuthStatus(false,e.message ?: "an incomprehensible error",-1)
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
            authScope.launch {
                userRepository.insertUser(
                    User(
                        result.user?.uid!!.toInt(),
                        userCreads.email,
                        userCreads.password
                    )
                )
            }
            AuthStatus(status, message, result.user?.uid!!.toInt())
        }
        catch (e:Exception){
            AuthStatus(false,e.message ?: "an incomprehensible error",-1)
        }
    }

}