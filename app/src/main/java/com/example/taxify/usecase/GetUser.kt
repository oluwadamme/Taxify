package com.example.taxify.usecase

import com.example.taxify.ServiceResult
import com.example.taxify.domain.UnterUser
import com.example.taxify.services.AuthorizationService
import com.example.taxify.services.UserService

class GetUser(
    val authService: AuthorizationService,
    val userService: UserService
) {

    suspend fun getUser(): ServiceResult<UnterUser?> {
        val getSession = authService.getSession()
        return when (getSession) {
            is ServiceResult.Failure -> getSession
            is ServiceResult.Value -> {
                if (getSession.value == null) getSession
                else getUserDetails(getSession.value.userId)
            }
        }
    }

    private suspend fun getUserDetails(uid: String): ServiceResult<UnterUser?> {
        return userService.getUserById(uid).let { getDetailsResult ->
            when (getDetailsResult) {
                    is ServiceResult.Failure -> ServiceResult.Failure(getDetailsResult.exception)
                is ServiceResult.Value -> ServiceResult.Value(getDetailsResult.value)
            }
        }
    }
}