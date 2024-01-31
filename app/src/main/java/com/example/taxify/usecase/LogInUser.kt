package com.example.taxify.usecase

import com.example.taxify.ServiceResult
import com.example.taxify.services.AuthorizationService
import com.example.taxify.services.LogInResult
import com.example.taxify.services.UserService

class LogInUser(
    val authService: AuthorizationService,
    val userService: UserService
) {

    suspend fun login(email: String, password: String): ServiceResult<LogInResult> {
        val authAttempt = authService.login(email, password)

       return if (authAttempt is ServiceResult.Value) {
            when (authAttempt.value) {
                is LogInResult.Success -> getUserDetails(authAttempt.value.user.userId)
                else -> authAttempt
            }
        } else authAttempt
    }


    private suspend fun getUserDetails(
        uid: String
    ): ServiceResult<LogInResult> {
        return userService.getUserById(uid).let { updateResult ->
            when (updateResult) {
                is ServiceResult.Failure -> ServiceResult.Failure(updateResult.exception)
                is ServiceResult.Value -> {
                    if (updateResult.value == null) ServiceResult.Failure(Exception("Null user in LogInUser"))
                    else ServiceResult.Value(LogInResult.Success(updateResult.value))
                }
            }
        }
    }
}