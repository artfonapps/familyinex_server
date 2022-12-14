package com.artfonapps

import com.artfonapps.features.auth.configureAuthRouting
import com.artfonapps.features.login.configureLoginRouting
import com.artfonapps.features.register.configureRegisterRouting
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.artfonapps.plugins.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

fun main() {
    val config = HikariConfig("hikari.properties")
    val dataSource = HikariDataSource(config)
    Database.connect(dataSource)

     embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
         // embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSecurity()
        configureSerialization()
        configureRouting()
        configureAuthRouting()
        configureLoginRouting()
        configureRegisterRouting()
    }.start(wait = true)
}
