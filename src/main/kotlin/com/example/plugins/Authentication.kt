package com.example.plugins

import com.example.domain.model.Endpoint
import com.example.domain.model.UserSession
import com.example.route.util.RouteUtil.CONFIGURATION_NAME
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*

fun Application.configureAuth() {

    install(Authentication) {
        session<UserSession>(name = CONFIGURATION_NAME) {
            validate { session ->
                session
            }
            challenge {
                call.respondRedirect(Endpoint.Unauthorized.path)
            }
        }
    }
}