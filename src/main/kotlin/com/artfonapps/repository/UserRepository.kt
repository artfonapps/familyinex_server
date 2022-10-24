package com.artfonapps.repository

import com.artfonapps.features.auth.AuthRemoteResponse
import com.artfonapps.features.login.LoginRemoteResponse
import com.artfonapps.features.register.RegisterRemoteResponse

interface UserRepository {
    fun getSessionIdByAuth(login: String): AuthRemoteResponse

    fun register(sessionId: String, passwordHash: String, salt: String): RegisterRemoteResponse

    fun login(sessionId: String, passwordHash: String): LoginRemoteResponse

    fun getUserByToken(token: String)
}