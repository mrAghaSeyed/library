package ir.sharif.library.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val username: String,
    val password: String,
    val nickname: String
)
