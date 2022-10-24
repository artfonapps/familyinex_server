package com.artfonapps.repository.impl

import com.artfonapps.db.user.User
import com.artfonapps.db.user.UserDTO
import com.artfonapps.db.userpass.UserPass
import com.artfonapps.db.userpass.UserPassDTO
import com.artfonapps.db.usersession.UserSession
import com.artfonapps.db.usersession.UserSessionDTO
import com.artfonapps.db.usertoken.UserToken
import com.artfonapps.exceptions.BaseDataException
import com.artfonapps.features.auth.AuthRemoteResponse
import com.artfonapps.features.login.LoginRemoteResponse
import com.artfonapps.features.register.RegisterRemoteResponse
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

    override fun register(sessionId: String, passwordHash: String, salt: String): RegisterRemoteResponse {
        val login = UserSession.fetchSession(sessionId) ?: throw BaseDataException.NoSessionIdException
        UserPass.insert(UserPassDTO(login, passwordHash, salt))
        val newUser = UserDTO(login, null, null)
        User.insert(newUser)
        val token = UUID.randomUUID().toString().substring(0, 31)
        UserToken.insert(login, token)
        val response = RegisterRemoteResponse(token, newUser)
        UserSession.deleteSession(sessionId)
        return response
    }

    override fun login(sessionId: String, passwordHash: String): LoginRemoteResponse {
        val login = UserSession.fetchSession(sessionId) ?: throw BaseDataException.NoSessionIdException
        if (!UserPass.checkIfPassIsValid(login, passwordHash)) throw BaseDataException.WrongPasswordException
        val user = User.fetchUser(login) ?: throw BaseDataException.NoUserException
        val token = UUID.randomUUID().toString().substring(0, 31)
        UserToken.insert(login, token)
        val response = LoginRemoteResponse(token, user)
        UserSession.deleteSession(sessionId)
        return response
    }

    override fun getUserByToken(token: String) {
        TODO("Not yet implemented")
    }
}