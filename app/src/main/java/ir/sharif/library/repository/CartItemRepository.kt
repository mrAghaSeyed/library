package ir.sharif.library.repository

import ir.sharif.library.dao.CartItemDao
import ir.sharif.library.entities.CartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartItemRepository(private val cartItemDao: CartItemDao) {
    suspend fun insert(cartItem: CartItem): Long = withContext(Dispatchers.IO) {
        cartItemDao.insert(cartItem)
    }


    suspend fun delete(userId: Long, bookId: Long) = withContext(Dispatchers.IO) {
        cartItemDao.delete(userId, bookId)
    }
}