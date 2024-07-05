package com.bibi.fungicheck.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bibi.fungicheck.data.model.Result
import com.bibi.fungicheck.data.api.ApiServiceGo
import com.bibi.fungicheck.data.response.LoginResponse
import com.bibi.fungicheck.data.response.RegisterResponse
import java.lang.Exception

class UserRepository private constructor(
    private val apiService: ApiServiceGo,
) {

    fun register(
        name: String,
        email: String,
        password: String,
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val result = apiService.register(name, email, password)
            emit(Result.Success(result))
        } catch (e: Exception) {
            Log.d(TAG, "register: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }

    }

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val result = apiService.login(email, password)
            emit(Result.Success(result))
        } catch (e: Exception) {
            Log.d(TAG, "login: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {

        private val TAG = UserRepository::class.java.simpleName

        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiServiceGo,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}