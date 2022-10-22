package com.artfonapps.db.usertoken

import org.jetbrains.exposed.sql.Table

object UserToken: Table("usertokens") {
    private val token = UserToken.varchar("token", 32)
    private val login = UserToken.varchar("login", 32)
}