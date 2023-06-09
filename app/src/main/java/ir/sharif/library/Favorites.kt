package ir.sharif.library

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.sharif.library.entities.Book
import ir.sharif.library.repository.FavoriteBookRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun Favorites(paddingValues: PaddingValues, vm: FavoritesViewModel = hiltViewModel()) {
    Column(
        Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Favorites", style = MaterialTheme.typography.headlineSmall)
        BooksList(showClose = true)
    }
}

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val favoriteBookRepository: FavoriteBookRepository) : ViewModel() {

    var favorites: List<Book> by mutableStateOf(listOf())

    fun getBooksList() {
        viewModelScope.launch {

        }
    }

}