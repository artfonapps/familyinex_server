package com.artfonapps.db.userpass

class UserPassDTO(
    val login: String,
    val passwordHash: String,
    val salt: String
)