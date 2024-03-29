package com.example.taxify.authentication.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.taxify.uicommon.ToastMessages
import com.example.taxify.navigation.SignUpKey
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.ScopedServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class LoginViewModel(
    private val backstack: Backstack,
    lookup: Any,
   // private val login: LogInUser
) : ScopedServices.Activated, CoroutineScope {
    internal var toastHandler: ((ToastMessages) -> Unit)? = null

    var email by mutableStateOf("")
        private set

    fun updateEmail(input: String) {
        email = input
    }

    var password by mutableStateOf("")
        private set

    fun updatePassword(input: String) {
        password = input
    }

    fun handleLogin() = launch(Dispatchers.Main) {
      //  val loginAttempt = login.login(email, password)
//        when (loginAttempt) {
////            is ServiceResult.Failure -> toastHandler?.invoke(ToastMessages.SERVICE_ERROR)
////            is ServiceResult.Value -> {
////                val result = loginAttempt.value
////                when (result) {
////                    is LogInResult.Success -> sendToDashboard(result.user)
////                    LogInResult.InvalidCredentials -> toastHandler?.invoke(ToastMessages.INVALID_CREDENTIALS)
////                }
////            }
//        }
    }

//    private fun sendToDashboard(user: UnterUser) {
//        when (user.type) {
//            "PASSENGER" -> backstack.setHistory(
//                History.of(PassengerDashboardKey()),
//                //Direction of navigation which is used for animation
//                StateChange.FORWARD
//            )
//            "DRIVER" -> backstack.setHistory(
//                History.of(DriverDashboardKey()),
//                //Direction of navigation which is used for animation
//                StateChange.FORWARD
//            )
//        }
//    }

    fun goToSignup() {
        backstack.goTo(SignUpKey())
    }

    override fun onServiceActive() = Unit

    override fun onServiceInactive() {
        canceller.cancel()
        toastHandler = null
    }

    private val canceller = Job()

    override val coroutineContext: CoroutineContext
        get() = canceller + Dispatchers.Main
}