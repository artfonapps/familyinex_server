package com.artfonapps.features.register

import com.artfonapps.db.user.UserDTO
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRemoteData(val authSessionId: String, val passwordHash: String, val salt: String)

@Serializable
data class RegisterRemoteResponse(val token: String, val user: UserDTO)
