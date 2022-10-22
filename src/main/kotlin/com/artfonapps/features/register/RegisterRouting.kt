package com.artfonapps.features.register

import com.artfonapps.utils.Routes
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureRegisterRouting() {

    routing {
        get(Routes.REGISTER) {
            val receive = call.receive(RegisterRemoteData::class)

        }
    }
}
