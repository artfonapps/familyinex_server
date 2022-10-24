package com.artfonapps.db.userpass

import com.artfonapps.db.user.User
import com.artfonapps.db.user.UserDTO
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object UserPass: Table("userspass") {
    private val login = UserPass.varchar("login", 32)
    private val salt = UserPass.varchar("salt", 32)
    private val passwordHash = UserPass.varchar("password_hash", 32)

    fun insert(userPassDTO: UserPassDTO) {
        transaction {
            UserPass.insert {
                it[login] = userPassDTO.login
                it[passwordHash] = userPassDTO.passwordHash
                it[salt] = userPassDTO.salt
            }
        }
    }

    fun checkIfPassIsValid(login: String, passwordHash: String): Boolean {
        return try {
            val userModel = UserPass.select { UserPass.login.eq(login) }.firstOrNull() ?: return false
            return userModel[UserPass.passwordHash] == passwordHash
        } catch (e: Exception) {
            false
        }
    }

    fun fetchUserSalt(login: String): String? {
        return try {
            val userModel = UserPass.select { UserPass.login.eq(login) }.single()
            userModel[salt]
        } catch (e: Exception) {
            null
        }
    }
}