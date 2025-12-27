package com.iqbalwork.kmpgithub

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iqbalwork.kmpgithub.data.DataResult
import com.iqbalwork.kmpgithub.data.model.UserResponse
import com.iqbalwork.kmpgithub.domain.GithubRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

/**
 * iqbalfauzi
 * Email: work.iqbalfauzi@gmail.com
 * Github: https://github.com/iqbalwork
 */
class MainViewModel(private val githubRepository: GithubRepository) : ViewModel() {

    private val _listUsers = MutableStateFlow<List<UserResponse>>(emptyList())
    val listUsers = _listUsers.asStateFlow()

    private val _user = MutableStateFlow<UserResponse?>(null)
    val user = _user.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    val userName = mutableStateOf("")

    fun onUserNameChanged(newUserName: String) {
        userName.value = newUserName
    }

    init {
        getAllUser()
    }

    fun searchUser() {
        githubRepository.findUser(userName.value)
            .onEach { result ->
                when (result) {
                    is DataResult.Error -> {
                        _errorMessage.update { result.t.message.orEmpty() }
                    }

                    is DataResult.Success -> {
                        _user.update { result.data }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun getAllUser() {
        githubRepository.getUsers()
            .onEach { result ->
                when (result) {
                    is DataResult.Error -> {
                        _errorMessage.update { result.t.message.orEmpty() }
                    }

                    is DataResult.Success -> {
                        _listUsers.update { result.data }
                    }
                }
            }
            .launchIn(viewModelScope)
    }

}
