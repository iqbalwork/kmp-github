package com.iqbalwork.kmpgithub.data.repository

import com.iqbalwork.kmpgithub.data.DataResult
import com.iqbalwork.kmpgithub.data.model.RepositoryResponse
import com.iqbalwork.kmpgithub.data.model.UserResponse
import com.iqbalwork.kmpgithub.data.network.ApiService
import com.iqbalwork.kmpgithub.domain.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * iqbalfauzi
 * Email: work.iqbalfauzi@gmail.com
 * Github: https://github.com/iqbalwork
 */
class GithubRepositoryImpl(private val apiService: ApiService) : GithubRepository {

    override fun getUsers(): Flow<DataResult<List<UserResponse>>> {
        return flow {
            try {
                val response = apiService.getUsers()
                if (response.isNotEmpty()) {
                    emit(DataResult.Success(response))
                } else {
                    emit(DataResult.Error(Exception("No data found")))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataResult.Error(e))
            }
        }
    }

    override fun findUser(username: String): Flow<DataResult<UserResponse>> {
        return flow {
            try {
                val response = apiService.findUser(username)
                emit(DataResult.Success(response))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataResult.Error(e))
            }
        }
    }

    override fun getUserRepositories(username: String): Flow<DataResult<List<RepositoryResponse>>> {
        return flow {
            try {
                val response = apiService.getUserRepositories(username)
                if (response.isNotEmpty()) {
                    emit(DataResult.Success(response))
                } else {
                    emit(DataResult.Error(Exception("No data found")))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emit(DataResult.Error(e))
            }
        }
    }


}
