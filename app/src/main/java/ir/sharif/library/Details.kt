package ir.sharif.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.sharif.library.entities.Book
import ir.sharif.library.entities.CartItem
import ir.sharif.library.entities.FavoriteBook
import ir.sharif.library.repository.BookRepository
import ir.sharif.library.repository.CartItemRepository
import ir.sharif.library.repository.FavoriteBookRepository
import ir.sharif.library.ui.theme.LibraryTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun Details(paddingValues: PaddingValues, vm: DetailsViewModel, id: Long) {
    vm.getBookDetail(id)
    Column(
        Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val book = vm.book
        if (book != null) {
            Text(
                text = book.title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(22.dp)) {
                Image(
                    modifier = Modifier
                        .size(144.dp, 216.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    painter = rememberAsyncImagePainter(model = book.cover),
                    contentDescription = "book cover",
                    contentScale = ContentScale.FillBounds
                )
                Column(
                    Modifier.padding(top = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Row {
                        Text(text = "Author: ")
                        Text(text = book.author, fontWeight = FontWeight.SemiBold)
                    }
                    Row {
                        Text(text = "Category: ")
                        Text(text = book.category, fontWeight = FontWeight.SemiBold)
                    }
                    Row {
                        Text(text = "Rating: ")
                        Text(text = "4.5/5", fontWeight = FontWeight.SemiBold)
                    }
                    Row {
                        Text(text = "Price: ")
                        Text(text = "$${book.price}", fontWeight = FontWeight.SemiBold)
                    }
                    Row {
                        Text(text = "Publisher: ")
                        Text(text = book.publisher, fontWeight = FontWeight.SemiBold)
                    }
                    Row {
                        Text(text = "Revision: ")
                        Text(text = "${book.revision}", fontWeight = FontWeight.SemiBold)
                    }
                    Row {
                        Text(text = "Publish Date: ")
                        Text(text = book.publishDate, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { vm.addToCart() },
                    shape = RoundedCornerShape(size = 4.dp)
                ) {
                    Text(text = "add to cart")
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { vm.addToFavorites() },
                    shape = RoundedCornerShape(size = 4.dp)
                ) {
                    Text(text = "add to favorites")
                }
            }
            Text(text = "Description:", fontWeight = FontWeight.SemiBold)
            Text(
                modifier = Modifier,
                text = book.description,
            )

        }
    }
}

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val cartItemRepository: CartItemRepository,
    private val favoritesRepository: FavoriteBookRepository
) : ViewModel() {
    var book: Book? by mutableStateOf(null)
    var bookId: Long? by mutableStateOf(null)
    fun getBookDetail(id: Long) {
        viewModelScope.launch {
            bookId = id
            book = bookRepository.getBookById(bookId!!)
        }
    }

    fun addToFavorites() {
        viewModelScope.launch {
            favoritesRepository.insert(FavoriteBook(userId = getUserId()!!, bookId = bookId!!))
        }
    }

    fun addToCart() {
        viewModelScope.launch {
            cartItemRepository.insert(CartItem(userId = getUserId()!!, bookId = bookId!!, count = 1))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    LibraryTheme {
        Details(PaddingValues(0.dp), viewModel(), 1)
    }
}