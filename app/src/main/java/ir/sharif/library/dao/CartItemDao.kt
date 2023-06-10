package ir.sharif.library.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import ir.sharif.library.entities.CartItem

@Dao
interface CartItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cartItem: CartItem): Long

    @Query("DELETE FROM cart_items WHERE userId = :userId AND bookId = :bookId")
    suspend fun delete(userId: String, bookId: Long)

    @Query("SELECT * FROM cart_items WHERE userId = :userId")
    suspend fun getCartByUserId(userId: String): List<CartItem>

    @Query("UPDATE cart_items SET count = count + 1 WHERE userId = :userId AND bookId = :bookId")
    suspend fun increaseCount(userId: String, bookId: Long)

    @Query("UPDATE cart_items SET count = count - 1 WHERE userId = :userId AND bookId = :bookId")
    suspend fun rawDecreaseCount(userId: String, bookId: Long)

    @Query("DELETE FROM cart_items WHERE userId = :userId AND bookId = :bookId AND count = 0")
    suspend fun deleteIfZero(userId: String, bookId: Long)

    @Transaction
    suspend fun decreaseCount(userId: String, bookId: Long) {
        rawDecreaseCount(userId, bookId)
        deleteIfZero(userId, bookId)
    }

}

