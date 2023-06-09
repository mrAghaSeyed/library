package ir.sharif.library

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import dagger.hilt.android.AndroidEntryPoint
import ir.sharif.library.bookcard.BookCard
import ir.sharif.library.entities.Book
import ir.sharif.library.ui.theme.LibraryTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LibraryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreenView()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { NavigationBar(navController = navController) }
    ) {
        NavigationGraph(navController = navController, paddingValues = it)
    }
}


@Composable
fun Cart(paddingValues: PaddingValues) {
    Column(
        Modifier
            .padding(paddingValues)
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
            .fillMaxHeight()
        , verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = "Cart", style = MaterialTheme.typography.headlineSmall)
        BooksList(Modifier.weight(1f), showClose = true, showCounter = true)
        Row {
            Text(
                text = "Total",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$50.00",
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {  }, shape = RoundedCornerShape(size = 4.dp)) {
            Text(text = "Proceed to Checkout")
        }
        Spacer(Modifier.size(0.dp))
    }
}

@Composable
fun BooksList(
    modifier: Modifier = Modifier,
    showClose: Boolean = false,
    showCounter: Boolean = false,
    books: List<Book> = listOf(Book(title = "book1", author = "seyed", cover = "https://covers.openlibrary.org/b/id/7085472-M.jpg"))
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), modifier = modifier) {
        items(items=books) { book ->
            BookCard(
                title = book.title,
                author = book.title,
                rate = "4.5/5",
                showClose = showClose,
                showCounter = showCounter,
                cover = rememberAsyncImagePainter(
                    model = book.cover,
                ),
                price = "$30.00"
            )
        }
        item { Spacer(modifier = Modifier.size(0.dp)) }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LibraryTheme {
        Cart(PaddingValues(0.dp))
    }
}