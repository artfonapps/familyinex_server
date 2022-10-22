package com.artfonapps.features.auth

import com.artfonapps.utils.Routes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureAuthRouting() {

    routing {
        post(Routes.AUTH) {
            AuthController(call).auth()
            return@post
        }
    }
}
