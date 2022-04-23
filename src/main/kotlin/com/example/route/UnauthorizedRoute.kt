package com.example.route

import com.example.domain.model.Endpoint
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.unauthorizedRoute() {

    get(Endpoint.Unauthorized.path) {
        call.respond(
            message = "UnAuthorized",
            status = HttpStatusCode.Unauthorized
        )
    }
}