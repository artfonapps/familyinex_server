package com.artfonapps.features.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRemoteData(val authSessionId: String, val passwordHash: String)

@Serializable
data class LoginRemoteResponse(val token: String)
