package ir.sharif.library.repository

import ir.sharif.library.dao.FavoriteBookDao
import ir.sharif.library.entities.Book
import ir.sharif.library.entities.FavoriteBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteBookRepository @Inject constructor(private val favoriteBookDao: FavoriteBookDao) {
    suspend fun insert(favoriteBook: FavoriteBook): Long = withContext(Dispatchers.IO) {
        favoriteBookDao.insert(favoriteBook)
    }

    suspend fun delete(userId: String, bookId: Long) = withContext(Dispatchers.IO) {
        favoriteBookDao.delete(userId, bookId)
    }

    suspend fun getFavoriteBooksByUserId(userId: String): List<Book> = withContext(Dispatchers.IO) {
        favoriteBookDao.getFavoriteBooksByUserId(userId)
    }
}
