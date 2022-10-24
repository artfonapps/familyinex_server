package com.artfonapps.features.login

import com.artfonapps.RepositoryModule
import com.artfonapps.exceptions.BaseDataException
import com.artfonapps.features.auth.valueValidation
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*


class LoginController(private val call: ApplicationCall) {

    suspend fun login() {
        val receive = call.receive(LoginRemoteData::class)
        if (!valueValidation(receive.authSessionId, receive.passwordHash)) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            try {
                call.respond(
                    RepositoryModule.getUserRepository()
                        .login(sessionId = receive.authSessionId, passwordHash = receive.passwordHash)
                )
            } catch (e: BaseDataException) {
                call.respond(HttpStatusCode.Unauthorized, e.message)
            }
            return
        }

    }
}
