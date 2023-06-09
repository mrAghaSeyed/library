package ir.sharif.library

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import ir.sharif.library.dao.FavoriteBookDao

@HiltAndroidApp
class LibraryApplication : Application()


@Module
@InstallIn(ViewModelComponent::class)
object DaggerModule {

    @Provides
    fun getFavoriteBookDao(
        @ApplicationContext appContext: Context
    ): FavoriteBookDao {
        return LibraryDatabase.getInstance(appContext).favoriteBookDao()
    }
}

