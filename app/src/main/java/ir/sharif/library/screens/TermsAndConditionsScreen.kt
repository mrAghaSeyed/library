package ir.sharif.library.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ir.sharif.library.R
import ir.sharif.library.SIGN_UP_ROUTE
import ir.sharif.library.components.HeadingTextComponent

@Composable
fun TermsAndConditionsScreen(navController: NavHostController) {

    HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions_header))

    navController.navigate(SIGN_UP_ROUTE)
}