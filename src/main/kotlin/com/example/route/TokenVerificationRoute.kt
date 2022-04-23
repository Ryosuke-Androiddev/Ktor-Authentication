package com.example.route

import com.example.domain.model.Endpoint
import com.example.domain.model.UserSession
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.tokenVerificationRoute() {

    post(Endpoint.TokenVerification.path) {
        call.sessions.set(UserSession("123456","Ryosuke"))
        call.respondRedirect(Endpoint.Authorized.path)
    }
}