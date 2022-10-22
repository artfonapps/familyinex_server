package com.artfonapps.db.user

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object User : Table("users") {
    private val login = User.varchar("login", 32)
    private val firstName = User.varchar("first_name", 32).nullable()
    private val lastName = User.varchar("last_name", 32).nullable()

    fun insert(userDTO: UserDTO) {
        transaction {
            User.insert {
                it[login] = userDTO.login
                it[firstName] = userDTO.firstName
                it[lastName] = userDTO.lastName
            }
        }
    }

    fun fetchUser(login: String): UserDTO? {
        return try {
            val userModel = User.select { User.login.eq(login) }.single()
            UserDTO(
                login = userModel[User.login],
                firstName = userModel[firstName],
                lastName = userModel[lastName]
            )
        } catch (e: Exception) {
            null
        }
    }
}