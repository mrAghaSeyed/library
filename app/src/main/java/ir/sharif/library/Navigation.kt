package ir.sharif.library

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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


@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            Home(paddingValues)
        }
        composable(BottomNavItem.Cart.route) {
            Cart(paddingValues)
        }
        composable(BottomNavItem.Favorites.route) {
            Favorites(paddingValues)
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

