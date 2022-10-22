package com.artfonapps.features.login

import com.artfonapps.utils.Routes
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {

    routing {
        get(Routes.LOGIN) {
            val receive = call.receive(LoginRemoteData::class)

        }
    }
}
