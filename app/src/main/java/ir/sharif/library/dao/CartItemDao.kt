package ir.sharif.library.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ir.sharif.library.entities.CartItem

@Dao
interface CartItemDao {
    @Insert
    suspend fun insert(favoriteBook: CartItem): Long

    @Query("DELETE FROM cart_items WHERE userId = :userId AND bookId = :bookId")
    suspend fun delete(userId: Long, bookId: Long)

    @Query("SELECT * FROM cart_items WHERE userId = :userId")
    suspend fun getCartByUserId(userId: Long): List<CartItem>
}

