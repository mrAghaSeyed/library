package ir.sharif.library.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import ir.sharif.library.BottomNavItem
import ir.sharif.library.data.RegistrationUIState
import ir.sharif.library.data.rules.Validator
import ir.sharif.library.data.rules.Validator.validateFirstName
import ir.sharif.library.data.rules.Validator.validatePrivacyPolicyAcceptance


class SignupViewModel: ViewModel() {

    private val TAG = SignupViewModel::class.simpleName


    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    lateinit var navController: NavHostController

    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignupUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }


            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
                if (!event.status){
                    registrationUIState.value = registrationUIState.value.copy(
                        privacyPolicyError = true
                    )
                }else{
                    registrationUIState.value = registrationUIState.value.copy(
                        privacyPolicyError = false
                    )
                }
                printState()
            }

            is SignupUIEvent.FirstNameSubmit -> {
                val result = validateFirstName(registrationUIState.value.firstName)
                registrationUIState.value = registrationUIState.value.copy(
                    firstNameError = !result.status
                )
            }
            is SignupUIEvent.LastNameSubmit -> {
                val result = validateFirstName(registrationUIState.value.lastName)
                registrationUIState.value = registrationUIState.value.copy(
                    lastNameError = !result.status
                )
            }
            is SignupUIEvent.EmailSubmit -> {
                val result = validateFirstName(registrationUIState.value.email)
                registrationUIState.value = registrationUIState.value.copy(
                    emailError = !result.status
                )
            }
            is SignupUIEvent.PasswordSubmit -> {
                val result = validateFirstName(registrationUIState.value.password)
                registrationUIState.value = registrationUIState.value.copy(
                    passwordError = !result.status
                )
            }
        }
    }


    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }

    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        navController.navigate(BottomNavItem.Home.route)
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }

    private fun createUserInFirebase(email: String, password: String) {

        signUpInProgress.value = true

        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_OnCompleteListener")
                Log.d(TAG, " isSuccessful = ${it.isSuccessful}")

                signUpInProgress.value = false
                if (it.isSuccessful) {
                    navController.navigate(BottomNavItem.Home.route)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception= ${it.message}")
                Log.d(TAG, "Exception= ${it.localizedMessage}")
            }
    }


    private fun validateDataWithRules() {
        val fNameResult = validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        val privacyPolicyResult =validatePrivacyPolicyAcceptance(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )


        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "lNameResult= $lNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )


        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status

    }

    fun isAllValidationsPassed(): Boolean {
        val fNameResult = validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )


        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        val privacyPolicyResult =validatePrivacyPolicyAcceptance(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )
        Log.d(TAG, "Inside_isAllValidationsPassed")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "lNameResult= $lNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        return  fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status
    }
}
