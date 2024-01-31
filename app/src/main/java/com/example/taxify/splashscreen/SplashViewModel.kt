package com.example.taxify.splashscreen

import android.util.Log
import com.example.taxify.ServiceResult
import com.example.taxify.navigation.DriverDashboardKey
import com.example.taxify.navigation.LoginKey
import com.example.taxify.navigation.PassengerDashboardKey
import com.example.taxify.domain.UnterUser
import com.example.taxify.usecase.GetUser
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.ScopedServices
import com.zhuinden.simplestack.StateChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class SplashViewModel(
    val backstack: Backstack,
    val getUser: GetUser
) : ScopedServices.Activated, CoroutineScope {

    private fun sendToLogin() {
        //clear backstack and replace with what we enter
        backstack.setHistory(
            History.of(LoginKey()),
            //Direction of navigation which is used for animation
            StateChange.FORWARD
        )
    }

    fun checkAuthState() = launch {
        val getUser = getUser.getUser()

        when (getUser) {
            //there's nothing else to do but send to the login page
            is ServiceResult.Failure -> sendToLogin()
            is ServiceResult.Value -> {
                if (getUser.value == null) sendToLogin()
                else sendToDashboard(getUser.value!!)
            }
        }
    }

    private fun sendToDashboard(user: UnterUser) {
        Log.d("VM_USER", user.toString())
        when (user.type) {
            "PASSENGER" -> backstack.setHistory(
                History.of((PassengerDashboardKey())),
                //Direction of navigation which is used for animation
                StateChange.FORWARD
            )
            "DRIVER" -> backstack.setHistory(
                History.of((DriverDashboardKey())),
                //Direction of navigation which is used for animation
                StateChange.FORWARD
            )
        }
    }

    //Lifecycle method to Fetch things if necessary
    override fun onServiceActive() {
        checkAuthState()
    }

    //Tear down
    override fun onServiceInactive() {
        canceller.cancel()
    }

    private val canceller = Job()

    override val coroutineContext: CoroutineContext
        get() = canceller + Dispatchers.Main
}