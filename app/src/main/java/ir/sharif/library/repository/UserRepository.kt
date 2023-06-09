package ir.sharif.library.repository

import ir.sharif.library.dao.UserDao
import ir.sharif.library.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {
    suspend fun insert(user: User): Long = withContext(Dispatchers.IO) {
        userDao.insert(user)
    }

    suspend fun update(user: User) = withContext(Dispatchers.IO) {
        userDao.update(user)
    }

    suspend fun getUserByUsernameAndPassword(username: String, password: String): User? = withContext(Dispatchers.IO) {
        userDao.getUserByUsernameAndPassword(username, password)
    }

    suspend fun getUserById(userId: Long): User = withContext(Dispatchers.IO) {
        userDao.getUserById(userId)
    }
}
