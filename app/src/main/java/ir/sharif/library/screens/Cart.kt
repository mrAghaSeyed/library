package ir.sharif.library.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.sharif.library.DETAIL_ROUTE
import ir.sharif.library.entities.Book
import ir.sharif.library.getUserId
import ir.sharif.library.repository.BookRepository
import ir.sharif.library.repository.CartItemRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@Composable
fun Cart(paddingValues: PaddingValues, vm: CartViewModel, navController: NavController) {
    vm.getCart()
    Column(
        Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        BooksList(
            Modifier.weight(1f),
            booksWithCount = vm.cart,
            showClose = true,
            showCounter = true,
            onIncrease = { vm.increase(it) },
            onDecrease = { vm.decrease(it) },
            onClose = { vm.delete(it) },
            onClick = { navController.navigate("$DETAIL_ROUTE/${it.id}") }
        )
        Row {
            Text(
                text = "Total",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$${vm.totalPrice}",
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { },
            shape = RoundedCornerShape(size = 4.dp)
        ) {
            Text(text = "Proceed to Checkout")
        }
        Spacer(Modifier.size(0.dp))
    }
}

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartItemRepository,
    private val bookRepository: BookRepository
) :
    ViewModel() {

    var cart: List<BookWithCount> by mutableStateOf(listOf())
    var booksCount: List<Int> by mutableStateOf(listOf())
    var totalPrice: Double by mutableStateOf(0.0)

    fun getCart() {
        viewModelScope.launch {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            val cartItems = repository.getCartByUserId(userId)
            booksCount = cartItems.map { it.count }
            cart = cartItems.map { BookWithCount(bookRepository.getBookById(it.bookId), it.count) }
            totalPrice = cart.sumOf { it.count * it.book.price }
        }
    }

    fun increase(book: Book) {
        viewModelScope.launch {
            repository.increaseCount(bookId = book.id, userId = getUserId()!!)
            getCart()
        }
    }

    fun decrease(book: Book) {
        viewModelScope.launch {
            repository.decreaseCount(bookId = book.id, userId = getUserId()!!)
            getCart()
        }
    }

    fun delete(book: Book) {
        viewModelScope.launch {
            repository.delete(bookId = book.id, userId = getUserId()!!)
            getCart()
        }
    }
}