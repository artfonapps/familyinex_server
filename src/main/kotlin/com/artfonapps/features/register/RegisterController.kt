package com.artfonapps.features.register

import com.artfonapps.RepositoryModule
import com.artfonapps.exceptions.BaseDataException
import com.artfonapps.features.auth.valueValidation
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*


class RegisterController(private val call: ApplicationCall) {

    suspend fun register() {
        val receive = call.receive(RegisterRemoteData::class)
        if (!valueValidation(receive.authSessionId, receive.salt, receive.passwordHash)) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            try {
                call.respond(
                    RepositoryModule.getUserRepository()
                        .register(
                            sessionId = receive.authSessionId,
                            passwordHash = receive.passwordHash,
                            salt = receive.salt
                        )
                )
            } catch (e: BaseDataException) {
                call.respond(HttpStatusCode.Unauthorized, e.message)
            }
            return
        }

    }
}
