package com.artfonapps.plugins

import com.artfonapps.utils.Routes
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        get(Routes.HOME) {
            call.respondText("Hello World!")
        }
    }
}
