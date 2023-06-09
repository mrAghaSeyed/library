package ir.sharif.library.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ir.sharif.library.entities.Book
import ir.sharif.library.entities.FavoriteBook

@Dao
interface FavoriteBookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteBook: FavoriteBook): Long

    @Query("DELETE FROM favorite_books WHERE userId = :userId AND bookId = :bookId")
    suspend fun delete(userId: Long, bookId: Long)

    @Transaction
    @Query("SELECT * FROM books WHERE id IN (SELECT bookId FROM favorite_books WHERE userId = :userId)")
    suspend fun getFavoriteBooksByUserId(userId: Long): List<Book>
}
