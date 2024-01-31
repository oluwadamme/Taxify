package com.example.taxify.usecase

import com.example.taxify.ServiceResult
import com.example.taxify.domain.UnterUser
import com.example.taxify.services.AuthorizationService
import com.example.taxify.services.UserService

class LogOutUser(
    val authService: AuthorizationService,
    val userService: UserService
) {

    suspend fun logout(user: UnterUser): ServiceResult<Unit> {
        authService.logout()
        userService.logOutUser(user)

        return ServiceResult.Value(Unit)
    }
}