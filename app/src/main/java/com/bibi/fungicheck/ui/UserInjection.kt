package com.bibi.fungicheck.ui

import com.bibi.fungicheck.data.api.ApiConfig
import com.bibi.fungicheck.data.repository.UserRepository

object UserInjection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}