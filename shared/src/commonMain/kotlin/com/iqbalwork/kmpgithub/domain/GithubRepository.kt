package com.iqbalwork.kmpgithub.domain

import com.iqbalwork.kmpgithub.data.DataResult
import com.iqbalwork.kmpgithub.data.model.RepositoryResponse
import com.iqbalwork.kmpgithub.data.model.UserResponse
import kotlinx.coroutines.flow.Flow

/**
 * iqbalfauzi
 * Email: work.iqbalfauzi@gmail.com
 * Github: https://github.com/iqbalwork
 */
interface GithubRepository {
    fun getUsers(): Flow<DataResult<List<UserResponse>>>
    fun findUser(username: String): Flow<DataResult<UserResponse>>
    fun getUserRepositories(username: String): Flow<DataResult<List<RepositoryResponse>>>
}
