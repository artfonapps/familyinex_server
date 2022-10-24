package com.artfonapps.features.register

import com.artfonapps.utils.Routes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRegisterRouting() {

    routing {
        post(Routes.REGISTER) {
            RegisterController(call).register()
            return@post
        }
    }
}
