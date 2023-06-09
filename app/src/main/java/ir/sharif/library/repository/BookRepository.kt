package ir.sharif.library.repository
import ir.sharif.library.dao.BookDao
import ir.sharif.library.entities.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookRepository @Inject constructor(private val bookDao: BookDao) {
    suspend fun insert(book: Book): Long = withContext(Dispatchers.IO) {
        bookDao.insert(book)
    }

    suspend fun update(book: Book) = withContext(Dispatchers.IO) {
        bookDao.update(book)
    }

    suspend fun getAllBooks() = withContext(Dispatchers.IO) {
        bookDao.getAllBooks()
    }

    suspend fun getBookById(bookId: Long) = withContext(Dispatchers.IO) {
        bookDao.getBookById(bookId)
    }
}
