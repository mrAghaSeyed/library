package ir.sharif.library.repository

import ir.sharif.library.dao.CartItemDao
import ir.sharif.library.entities.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartItemRepository @Inject constructor(private val cartItemDao: CartItemDao) {
    suspend fun insert(cartItem: CartItem): Long = withContext(Dispatchers.IO) {
        cartItemDao.insert(cartItem)
    }


    suspend fun delete(userId: String, bookId: Long) = withContext(Dispatchers.IO) {
        cartItemDao.delete(userId, bookId)
    }

    suspend fun getCartByUserId(userId: String) = withContext(Dispatchers.IO) {
        cartItemDao.getCartByUserId(userId)
    }
    suspend fun increaseCount(userId: String, bookId: Long) = withContext(Dispatchers.IO) {
        cartItemDao.increaseCount(userId, bookId)
    }
    suspend fun decreaseCount(userId: String, bookId: Long) = withContext(Dispatchers.IO) {
        cartItemDao.decreaseCount(userId, bookId)
    }
}
