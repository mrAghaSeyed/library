package ir.sharif.library

import android.app.Application
import android.content.Context
import com.google.firebase.FirebaseApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.sharif.library.dao.BookDao
import ir.sharif.library.dao.CartItemDao
import ir.sharif.library.dao.FavoriteBookDao

@HiltAndroidApp
class LibraryApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}


@Module
@InstallIn(ViewModelComponent::class)
object DaggerModule {

    @Provides
    fun getFavoriteBookDao(
        @ApplicationContext appContext: Context
    ): FavoriteBookDao {
        return LibraryDatabase.getInstance(appContext).favoriteBookDao()
    }

    @Provides
    fun getBookDao(
        @ApplicationContext appContext: Context
    ): BookDao {
        return LibraryDatabase.getInstance(appContext).bookDao()
    }

    @Provides
    fun getCartItemDoa(
        @ApplicationContext appContext: Context
    ): CartItemDao {
        return LibraryDatabase.getInstance(appContext).cartItemDao()
    }

}

