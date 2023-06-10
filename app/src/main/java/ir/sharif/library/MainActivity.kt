package ir.sharif.library

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import ir.sharif.library.components.AppToolbar
import ir.sharif.library.screens.HomeViewModel
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
                    NavigationGraph()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenView(navController: NavHostController, title: String, content: @Composable (PaddingValues) -> Unit) {
    val logoutViewModel: LogoutViewModel = viewModel()
    Scaffold(
        topBar = {
            AppToolbar(
                toolbarTitle = title,
                logoutButtonClicked = {
                    logoutViewModel.logout(navController)
                },
                navigationIconClicked = {
//                    coroutineScope.launch {
//                        scaffoldState.drawerState.open()
                },

                )
        }, bottomBar = { NavigationBar(navController = navController) },
        content = content
    )
}


class LogoutViewModel : ViewModel() {
    private val TAG = HomeViewModel::class.simpleName
    fun logout(navController: NavController) {

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()

        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "Inside sign out success")
                navController.navigate(LOGIN_ROUTE)
            } else {
                Log.d(TAG, "Inside sign out is not complete")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }

}