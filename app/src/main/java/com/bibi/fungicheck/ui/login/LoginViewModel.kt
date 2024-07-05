package com.bibi.fungicheck.ui.login

import androidx.lifecycle.ViewModel
import com.bibi.fungicheck.data.repository.UserRepository

class LoginViewModel(
    private val userRepo: UserRepository,
) : ViewModel() {

    fun login(email: String, password: String) = userRepo.login(email, password)
}