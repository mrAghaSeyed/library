package ir.sharif.library.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ir.sharif.library.AppRouter.AppRouter
import ir.sharif.library.AppRouter.Screen
import ir.sharif.library.MainScreenView
import ir.sharif.library.screens.LoginScreen
import ir.sharif.library.screens.SignUpScreen
import ir.sharif.library.screens.TermsAndConditionsScreen

@Composable
fun LibraryApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Crossfade(targetState = AppRouter.currentScreen) { currentState->
            when(currentState.value){
                is Screen.SignUpScreen ->{
                    SignUpScreen()
                }
                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }
                is Screen.LoginScreen ->{
                    LoginScreen()
                }
                is Screen.HomeScreen ->{
                    MainScreenView()
                }
            }
        }

    }
}