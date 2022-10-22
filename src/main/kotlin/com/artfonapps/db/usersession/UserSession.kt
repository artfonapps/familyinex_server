package com.artfonapps.db.usersession

import com.artfonapps.db.user.User
import com.artfonapps.db.user.UserDTO
import com.artfonapps.db.usertoken.UserToken
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.timestamp
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

object UserSession : Table("usersessions") {
    private val id = UserSession.varchar("id", 32)
    private val login = UserSession.varchar("login", 32)
    private val timestamp = UserToken.timestamp("creationDate")

    fun insert(userSessionDTO: UserSessionDTO): String {
        val sessionId: String = UUID.randomUUID().toString().substring(0, 31)
        transaction {
            UserSession.insert {
                it[login] = userSessionDTO.login
                it[id] = sessionId
            }
        }
        return sessionId
    }

    fun hasSession(login: String, id: String): Boolean {
        return try {
            UserSession.select { (UserSession.login.eq(login)).and(UserSession.id.eq(id)) }.firstOrNull() != null
        } catch (e: Exception) {
            false
        }
    }
}