package curs.academy.data.repositoryimpl

import curs.academy.data.database.dao.UserDao
import curs.academy.data.models.UserModel
import curs.academy.domain.models.User
import curs.academy.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    private val job = SupervisorJob()
    private val userScope = CoroutineScope(job + Dispatchers.IO)

    override fun insertUser(user: User) {
        userScope.launch(Dispatchers.IO) {
            userDao.insertUser(UserModel.map(user = user))
        }
    }

    override fun deleteUser(id: Int) {
        userScope.launch {
            userDao.deleteUser(id)
        }
    }

    override fun updateLogin(newLogin: String, id: Int) {
        userScope.launch{
            userDao.updateLogin(newLogin, id)
        }
    }

    override fun updatePassword(newPassword: String, id: Int) {
        userScope.launch{
            userDao.updatePassword(newPassword, id)
        }
    }

    override suspend fun getUserId(login: String, password: String): String {
        return userScope.async {
           userDao.getUserId(login, password)
        }.await()
    }

    override suspend fun getUserByUserId(userId: String): User {
        return userScope.async {
            userDao.getUserByUserId(userId).toUser()
        }.await()
    }

    override suspend fun getAllUser(): List<User> {
        return userScope.async {
            userDao.getAllUser().map { it.toUser() }
        }.await()
    }
}