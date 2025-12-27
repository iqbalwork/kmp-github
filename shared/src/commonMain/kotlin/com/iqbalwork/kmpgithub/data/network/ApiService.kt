package com.iqbalwork.kmpgithub.data.network

import com.iqbalwork.kmpgithub.data.model.RepositoryResponse
import com.iqbalwork.kmpgithub.data.model.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

/**
 * iqbalfauzi
 * Email: work.iqbalfauzi@gmail.com
 * Github: https://github.com/iqbalwork
 */
class ApiService(private val httpClient: HttpClient) {

    suspend fun getUsers(): List<UserResponse> {
        return httpClient.get("users").body()
    }

    suspend fun findUser(username: String): UserResponse {
        return httpClient.get("users/$username").body()
    }

    suspend fun getUserRepositories(username: String): List<RepositoryResponse> {
        return httpClient.get("users/$username/repos").body()
    }
}
