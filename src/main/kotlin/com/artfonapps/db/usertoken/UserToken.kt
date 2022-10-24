package com.artfonapps.db.usertoken

import com.artfonapps.db.userpass.UserPass
import com.artfonapps.db.userpass.UserPassDTO
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object UserToken : Table("usertokens") {
    private val token = UserToken.varchar("token", 32)
    private val login = UserToken.varchar("login", 32)

    fun insert(login: String, token: String) {
        transaction {
            UserToken.insert {
                it[UserToken.login] = login
                it[UserToken.token] = token
            }
        }
    }

    fun checkIfTokenIsValid(token: String): Boolean {
        return try {
            UserToken.select { UserToken.token.eq(token) }.firstOrNull() != null
        } catch (e: Exception) {
            false
        }
    }
}