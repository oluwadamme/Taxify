package com.example.taxify.services

import com.example.taxify.ServiceResult
import com.example.taxify.domain.UnterUser

interface UserService {

    suspend fun getUserById(userId: String): ServiceResult<UnterUser?>
    suspend fun updateUser(user: UnterUser): ServiceResult<UnterUser?>

    suspend fun initializeNewUser(user: UnterUser): ServiceResult<UnterUser?>

    suspend fun logOutUser(user: UnterUser): Unit
}