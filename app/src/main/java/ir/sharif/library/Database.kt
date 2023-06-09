package ir.sharif.library

import android.content.Context
import android.provider.ContactsContract.Data
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.sharif.library.dao.BookDao
import ir.sharif.library.dao.CartItemDao
import ir.sharif.library.dao.FavoriteBookDao
import ir.sharif.library.dao.UserDao
import ir.sharif.library.entities.Book
import ir.sharif.library.entities.CartItem
import ir.sharif.library.entities.FavoriteBook
import ir.sharif.library.entities.User

@Database(
    entities = [(Book::class), (User::class), (FavoriteBook::class), (CartItem::class)],
    version = 1,
    exportSchema = false
)
abstract class LibraryDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao
    abstract fun favoriteBookDao(): FavoriteBookDao
    abstract fun userDao(): UserDao
    abstract fun cartItemDao(): CartItemDao


    companion object {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        fun getInstance(appContext: Context): LibraryDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        appContext,
                        LibraryDatabase::class.java,
                        "library_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}