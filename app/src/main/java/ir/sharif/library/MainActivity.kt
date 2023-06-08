package ir.sharif.library

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Printer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemInfo
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ir.sharif.library.bookcard.BookCard
import ir.sharif.library.menuitem.MenuItem
import ir.sharif.library.menuitem.State
import ir.sharif.library.ui.theme.LibraryTheme
import androidx.compose.runtime.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LibraryTheme {
                // A surface container using the 'background' color from the theme
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
        NavigationGraph(navController = navController)
    }
}


@Composable
fun Home() {
    Column {
        Text(modifier = Modifier.padding(start = 20.dp, top=20.dp) ,text = "Home", style = MaterialTheme.typography.headlineSmall)
        BooksList()
    }

}


@Composable
fun Cart() {
    Column {
        Text(modifier = Modifier.padding(start = 20.dp, top=20.dp) ,text = "Cart", style = MaterialTheme.typography.headlineSmall)
        BooksList(showClose = true, showCounter = true)
    }
}

@Composable
fun Favorites() {
    Column {
        Text(modifier = Modifier.padding(start = 20.dp, top=20.dp) ,text = "Favorites", style = MaterialTheme.typography.headlineSmall)
        BooksList(showClose = true)
    }
}


@Composable
fun BooksList(showClose: Boolean = false, showCounter: Boolean = false) {
    LazyColumn(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(10) { index ->
            BookCard(
                title = "Book $index",
                author = "seyed",
                rate = "4.5/5",
                showClose = showClose,
                showCounter = showCounter,
                cover = painterResource(
                    id = R.drawable.book_card_cover
                ),
                price = "$30.00"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LibraryTheme {
        Home()
    }
}