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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.sharif.library.entities.Book
import kotlinx.coroutines.launch



@Composable
fun Home(paddingValues: PaddingValues, vm: HomeViewModel = viewModel()) {
    vm.getBooksList()
    Column(
        Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Home", style = MaterialTheme.typography.headlineSmall)
        BooksList(books = vm.bookListResponse, showCounter = true)
    }
}



class HomeViewModel : ViewModel() {
    var bookListResponse: List<Book> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getBooksList() {
        viewModelScope.launch {
            val apiService = OpenLibraryService.getInstance()
            val olids =
                "OLID:OL9952186M,OLID:OL7289325M,OLID:OL26335454M,OLID:OL10744956M,OLID:OL24319427M,OLID:OL3284292M"
            try {
                val movieList = apiService.getBookDetails(olids)
                bookListResponse = movieList.map { (olid, details) ->
                    Book(
                        title = details.title,
                        author = details.authors.first().name,
                        cover = details.cover.medium,
                        olid = olid,
                    )
                }
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}