package com.bibi.fungicheck.ui.register

import androidx.lifecycle.ViewModel
import com.bibi.fungicheck.data.repository.UserRepository

class RegisterViewModel(private val userRepo: UserRepository) : ViewModel() {
    fun register(name: String, email: String, password: String) =
        userRepo.register(name, email, password)
}