package ir.sharif.library.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ir.sharif.library.entities.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    suspend fun getUserByUsernameAndPassword(username: String, password: String): User?

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: Long): User
}
