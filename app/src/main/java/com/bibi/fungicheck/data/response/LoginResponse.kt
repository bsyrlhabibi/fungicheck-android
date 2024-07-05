package com.bibi.fungicheck.data.response

data class LoginResponse(
    val message: String,
    val data: LoginData?
)

data class LoginData(
    val user: UserData?,
    val access_token: String
)

data class UserData(
    val name: String,
    val email: String
)
