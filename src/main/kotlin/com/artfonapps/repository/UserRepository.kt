package com.artfonapps.repository

import com.artfonapps.features.auth.AuthRemoteResponse

interface UserRepository {
    fun getSessionIdByAuth(login: String): AuthRemoteResponse

    fun register(sessionId: String, password: String, salt: String)

    fun login(sessionId: String, passwordHash: String)

    fun getUserByToken(token: String)
}