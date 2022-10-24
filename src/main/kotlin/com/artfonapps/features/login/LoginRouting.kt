package com.artfonapps.features.login

import com.artfonapps.utils.Routes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {

    routing {
        post(Routes.LOGIN) {
            LoginController(call).login()
            return@post
        }
    }
}
