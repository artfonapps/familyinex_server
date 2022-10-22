package com.artfonapps.features.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRemoteData(val login: String)

@Serializable
data class AuthRemoteResponse(val authSessionId: String, val salt: String, val scenario: String)

