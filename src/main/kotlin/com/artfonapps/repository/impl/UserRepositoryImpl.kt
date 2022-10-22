package com.artfonapps.repository.impl

import com.artfonapps.db.userpass.UserPass
import com.artfonapps.db.usersession.UserSession
import com.artfonapps.db.usersession.UserSessionDTO
import com.artfonapps.features.auth.AuthRemoteResponse
import com.artfonapps.repository.UserRepository
import java.util.UUID

object UserRepositoryImpl : UserRepository {
    override fun getSessionIdByAuth(login: String): AuthRemoteResponse {
        val sessionId = UserSession.insert(UserSessionDTO(login))
        val userSalt = UserPass.fetchUserSalt(login)
        userSalt?.let {
            return AuthRemoteResponse(sessionId, it, "login")
        }
        return AuthRemoteResponse(sessionId, UUID.randomUUID().toString().substring(0, 31), "register")
    }

    override fun register(sessionId: String, password: String, salt: String) {
        TODO("Not yet implemented")
    }

    override fun login(sessionId: String, passwordHash: String) {
        TODO("Not yet implemented")
    }

    override fun getUserByToken(token: String) {
        TODO("Not yet implemented")
    }
}