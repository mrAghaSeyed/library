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
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.sharif.library.entities.Book
import ir.sharif.library.repository.FavoriteBookRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun Favorites(paddingValues: PaddingValues, vm: FavoritesViewModel, navController: NavController) {
    vm.getFavorites()
    Column(
        Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BooksList(
            showClose = true,
            books = vm.favorites,
            onClose = { vm.removeFavorite(it) },
            onClick = { navController.navigate("$DETAIL_ROUTE/${it.id}") })
    }
}

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: FavoriteBookRepository) :
    ViewModel() {

    var favorites: List<Book> by mutableStateOf(listOf())

    fun getFavorites() {
        viewModelScope.launch {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            favorites = repository.getFavoriteBooksByUserId(userId)
        }
    }

    fun removeFavorite(book: Book) {
        viewModelScope.launch {
            repository.delete(getUserId()!!, book.id)
            getFavorites()
        }
    }
}