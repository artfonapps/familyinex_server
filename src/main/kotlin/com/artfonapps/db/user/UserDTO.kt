package com.artfonapps.db.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class UserDTO(
    @SerialName("login")
    val login: String,
    @SerialName("first_name")
    val firstName: String?,
    @SerialName("last_name")
    val lastName: String?
)