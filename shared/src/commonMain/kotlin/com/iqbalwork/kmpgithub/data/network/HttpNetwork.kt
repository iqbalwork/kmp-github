package com.iqbalwork.kmpgithub.data.network

import com.iqbalwork.BuildKonfig
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import sp.bvantur.inspektify.ktor.AutoDetectTarget
import sp.bvantur.inspektify.ktor.InspektifyKtor
import sp.bvantur.inspektify.ktor.LogLevel

/**
 * iqbalfauzi
 * Email: work.iqbalfauzi@gmail.com
 * Github: https://github.com/iqbalwork
 */
object HttpNetwork {

    fun createHttpClient(
        engine: HttpClientEngine
    ): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            if (BuildKonfig.DEBUG) {
                install(InspektifyKtor) {
                    logLevel = LogLevel.All
                    autoDetectEnabledFor = setOf(AutoDetectTarget.Android, AutoDetectTarget.Apple)
                    shortcutEnabled = true
                }
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Napier.v(tag = "[KTOR]", message = message)
                        }
                    }
                    level = io.ktor.client.plugins.logging.LogLevel.ALL
                }
            }
            install(Auth) {
                bearer {
//                    sendWithoutRequest { true }
                    loadTokens {
                        BearerTokens(BuildKonfig.GITHUB_TOKEN, "")
                    }
                }
            }
            defaultRequest {
                url(BuildKonfig.BASE_URL)
            }
        }
    }
}
