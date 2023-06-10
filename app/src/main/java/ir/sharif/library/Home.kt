package ir.sharif.library

import android.util.Log
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import ir.sharif.library.AppRouter.AppRouter
import ir.sharif.library.AppRouter.Screen
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.sharif.library.entities.Book
import ir.sharif.library.entities.CartItem
import ir.sharif.library.repository.BookRepository
import ir.sharif.library.repository.CartItemRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun Home(paddingValues: PaddingValues, viewModel: HomeViewModel, navController: NavController) {
    viewModel.getBooksList()
    Column(
        Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Home", style = MaterialTheme.typography.headlineSmall)
        BooksList(
            books = viewModel.bookListResponse,
            showClose = true,
            onClose = { viewModel.addToFavorites(it) },
            onClick = { navController.navigate("$DETAIL_ROUTE/${it.id}") })
    }
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val cartItemRepository: CartItemRepository
) : ViewModel() {
    var bookListResponse: List<Book> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    private val TAG = HomeViewModel::class.simpleName

    fun getBooksList() {
        viewModelScope.launch {
            val apiService = OpenLibraryService.getInstance()
            val olids =
                "OLID:OL9952186M,OLID:OL7289325M,OLID:OL26335454M,OLID:OL10744956M,OLID:OL24319427M,OLID:OL3284292M"
            try {
                val movieList = apiService.getBookDetails(olids)
                val books = movieList.map { (olid, details) ->
                    Book(
                        title = details.title,
                        author = details.authors.first().name,
                        cover = details.cover.medium,
                        olid = olid,
                    )
                }
                books.forEach { bookRepository.insert(it) }
                bookListResponse = bookRepository.getAllBooks()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun addToFavorites(book: Book) {
        viewModelScope.launch {
            cartItemRepository.insert(CartItem(userId = 1, bookId = book.id, count = 1))
        }
    }


    fun logout() {

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign out success")
                AppRouter.navigateTo(Screen.LoginScreen)
            } else {
                Log.d(TAG, "Inside sign out is not complete")
            }
        }

        firebaseAuth.addAuthStateListener(authStateListener)

    }
}