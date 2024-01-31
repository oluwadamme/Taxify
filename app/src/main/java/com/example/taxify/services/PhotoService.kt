package com.example.taxify.services

import com.example.taxify.ServiceResult


interface PhotoService {
    suspend fun attemptUserAvatarUpdate(url: String): ServiceResult<String>
}