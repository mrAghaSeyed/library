package ir.sharif.library.screens

import android.util.Log
import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import ir.sharif.library.LOGIN_ROUTE
import ir.sharif.library.R
import ir.sharif.library.TERMS_AND_CONDITIONS_ROUTE
import ir.sharif.library.components.ButtonComponent
import ir.sharif.library.components.CheckboxComponent
import ir.sharif.library.components.ClickableLoginTextComponent
import ir.sharif.library.components.DividerTextComponent
import ir.sharif.library.components.HeadingTextComponent
import ir.sharif.library.components.MyTextFieldComponent
import ir.sharif.library.components.NormalTextComponent
import ir.sharif.library.components.PasswordTextFieldComponent
import ir.sharif.library.data.signup.SignupUIEvent
import ir.sharif.library.data.signup.SignupViewModel

@Composable
fun SignUpScreen(navController: NavHostController, signupViewModel: SignupViewModel = viewModel()) {
    signupViewModel.navController = navController
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        Column(modifier = Modifier.fillMaxSize()) {

            NormalTextComponent(value = stringResource(id = R.string.hello))
            HeadingTextComponent(value = stringResource(id = R.string.create_account))
            Spacer(modifier = Modifier.height(20.dp))

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.profile),
                onTextSubmit = {
                    signupViewModel.onEvent(SignupUIEvent.FirstNameSubmit)
                },
                onTextChanged = {
                    signupViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                },
                errorStatus = signupViewModel.registrationUIState.value.firstNameError,
                errorMessage = "Invalid input"

            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.last_name),
                painterResource = painterResource(id = R.drawable.profile),
                onTextChanged = {
                    signupViewModel.onEvent(SignupUIEvent.LastNameChanged(it))
                },
                errorStatus = signupViewModel.registrationUIState.value.lastNameError,
                errorMessage = "Invalid input",
                onTextSubmit = { signupViewModel.onEvent(SignupUIEvent.LastNameSubmit) }
            )

            MyTextFieldComponent(
                labelValue = stringResource(id = R.string.email),
                painterResource = painterResource(id = R.drawable.message),
                onTextChanged = {
                    signupViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                },
                errorStatus = signupViewModel.registrationUIState.value.emailError,
                errorMessage = "Invalid email address",
                onTextSubmit = { signupViewModel.onEvent(SignupUIEvent.EmailSubmit) }

            )

            PasswordTextFieldComponent(
                labelValue = stringResource(id = R.string.password),
                painterResource = painterResource(id = R.drawable.ic_lock),
                onTextSelected = {
                    signupViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                },
                errorStatus = signupViewModel.registrationUIState.value.passwordError,
            )

            CheckboxComponent(value = stringResource(id = R.string.terms_and_conditions),
                onTextSelected = {
                    navController.navigate(TERMS_AND_CONDITIONS_ROUTE)
                },
                onCheckedChange = {
                    signupViewModel.onEvent(SignupUIEvent.PrivacyPolicyCheckBoxClicked(it))
                }
            )

            ButtonComponent(
                value = stringResource(id = R.string.register),
                onButtonClicked = {
                    signupViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)
                },
                isEnabled = signupViewModel.isAllValidationsPassed()
            )

            Spacer(modifier = Modifier.height(20.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                navController.navigate(LOGIN_ROUTE)
            })
        }

    }

    if (signupViewModel.signUpInProgress.value) {
        CircularProgressIndicator()
    }
}

