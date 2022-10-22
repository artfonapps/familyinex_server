package com.artfonapps.features.auth

import com.artfonapps.plugins.configureRouting
import com.artfonapps.utils.Routes
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class AuthTest {
    @Test
    fun testValidAuth() = testApplication {
        application {
            configureRouting()
        }
        client.post(Routes.HOME).apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}