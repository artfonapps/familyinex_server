package com.artfonapps.features.auth

import com.artfonapps.RepositoryModule
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*


class AuthController(private val call: ApplicationCall) {

    suspend fun auth() {
        val receive = call.receive(AuthRemoteData::class)
        if (!valueValidation(receive.login)) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            call.respond(RepositoryModule.getUserRepository().getSessionIdByAuth(receive.login))
            return
        }

    }
}

fun valueValidation(vararg values: String): Boolean {
    values.forEach {
        if (it.isEmpty() || it.length > 32) return false
    }
    return true
}