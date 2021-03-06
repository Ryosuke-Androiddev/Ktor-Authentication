package com.example.route

import com.example.domain.model.ApiRequest
import com.example.domain.model.Endpoint
import com.example.domain.model.UserSession
import com.example.route.util.RouteUtil.AUDIENCE
import com.example.route.util.RouteUtil.ISSUER
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.tokenVerificationRoute(app: Application) {

    post(Endpoint.TokenVerification.path) {

        val request = call.receive<ApiRequest>()

        if (request.tokenId.isNotEmpty()) {
            val result = verifyGoogleTokenId(tokenId = request.tokenId)
            if (result != null) {
                val name = result.payload["name"].toString()
                val emailAddress = result.payload["email"].toString()

                app.log.info("TOKEN VERIFICATION SUCCESSFULLY VERIFIED: $name, $emailAddress")

                call.sessions.set(UserSession("123456","Ryosuke"))
                call.respondRedirect(Endpoint.Authorized.path)
            } else {
                app.log.info("TOKEN VERIFICATION FAILED")
                call.respondRedirect(Endpoint.Unauthorized.path)
            }
        } else {
            app.log.info("EMPTY TOKEN ID")
            call.respondRedirect(Endpoint.Unauthorized.path)
        }
    }
}

fun verifyGoogleTokenId(tokenId: String): GoogleIdToken? {

    return try {

        val verifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory())
            .setAudience(listOf(AUDIENCE))
            .setIssuer(ISSUER)
            .build()

        verifier.verify(tokenId)

    } catch (e: Exception) {
        null
    }
}