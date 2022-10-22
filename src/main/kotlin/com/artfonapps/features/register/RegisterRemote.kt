package com.artfonapps.features.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRemoteData(val authSessionId: String, val passwordHash: String)

@Serializable
data class RegisterRemoteResponse(val token: String)
