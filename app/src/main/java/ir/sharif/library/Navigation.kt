package ir.sharif.library

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import ir.sharif.library.screens.LoginScreen
import ir.sharif.library.screens.SignUpScreen
import ir.sharif.library.screens.TermsAndConditionsScreen
import ir.sharif.library.ui.theme.LibraryTheme

sealed class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
) {
    object Home : BottomNavItem(
        name = "Home",
        route = "home",
        icon = Icons.Rounded.Home,
    )

    object Cart : BottomNavItem(
        name = "Cart",
        route = "Cart",
        icon = Icons.Rounded.ShoppingCart,
    )

    object Favorites : BottomNavItem(
        name = "Favorites",
        route = "favorites",
        icon = Icons.Rounded.Favorite,
    )
}

const val DETAIL_ROUTE = "detail"
const val SIGN_UP_ROUTE = "sign-up"
const val LOGIN_ROUTE = "login"
const val TERMS_AND_CONDITIONS_ROUTE = "terms-and-conditions"

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val uid = getUserId()
    val sourceDestination = when (uid == null) {true -> LOGIN_ROUTE else -> BottomNavItem.Home.route}
    NavHost(navController, startDestination = sourceDestination) {
        composable(BottomNavItem.Home.route) {
            MainScreenView(navController, BottomNavItem.Home.name) {
                Home(it, hiltViewModel(), navController)
            }
        }
        composable(BottomNavItem.Cart.route) {
            MainScreenView(navController, BottomNavItem.Cart.name) {
                Cart(it, hiltViewModel(), navController)
            }
        }
        composable(BottomNavItem.Favorites.route) {
            MainScreenView(navController, BottomNavItem.Favorites.name) {
                Favorites(it, hiltViewModel(), navController)
            }
        }
        composable("$DETAIL_ROUTE/{bookId}") { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getString("bookId")?.toLong() ?: 0
            MainScreenView(navController, "Details") {
                Details(it, hiltViewModel(), bookId)
            }
        }

        composable(SIGN_UP_ROUTE) {
            SignUpScreen(navController)
        }
        composable(TERMS_AND_CONDITIONS_ROUTE) {
            TermsAndConditionsScreen(navController)
        }
        composable(LOGIN_ROUTE) {
            LoginScreen(navController)
        }

    }
}


@Composable
fun NavigationBar(navController: NavHostController) = androidx.compose.material3.NavigationBar {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    listOf(BottomNavItem.Home, BottomNavItem.Favorites, BottomNavItem.Cart).forEach { item ->

        val selected = item.route == "home"
        NavigationBarItem(
            selected = currentRoute == item.route,
            onClick = {
                navController.navigate(item.route)
            },
            label = {
                Text(
                    text = item.name,
                    fontWeight = FontWeight.SemiBold,
                )
            },
            icon = {
                Icon(
                    imageVector = item.icon,
                    contentDescription = "${item.name} Icon",
                )
            }
        )
    }
}

fun getUserId() = FirebaseAuth.getInstance().currentUser?.uid