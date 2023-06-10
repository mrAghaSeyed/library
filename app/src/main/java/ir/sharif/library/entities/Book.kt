package ir.sharif.library.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "books", indices = [
    Index(value = ["olid"], unique = true)
])
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val olid: String = "",
    val title: String,
    val author: String,
    val price: Double = 20.00,
    val cover: String,
    val description: String = "",
    val revision: Int = 1,
    val publisher: String = "",
    val publishDate: String = "",
    val category: String = "",
)
