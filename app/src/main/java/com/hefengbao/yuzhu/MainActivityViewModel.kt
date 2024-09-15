package com.hefengbao.yuzhu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.model.AuthToken
import com.hefengbao.yuzhu.data.model.UserData
import com.hefengbao.yuzhu.data.repository.AuthRepository
import com.hefengbao.yuzhu.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: UserDataRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    val uiState: StateFlow<MainActivityUiState> = repository.getUserData().map {

        it.domain?.let { domain -> AppStatus.domain(domain) }
        it.accessToken?.let { token ->
            AppStatus.accessToken(token)
        }

        MainActivityUiState.Success(
            userData = UserData(
                domain = it.domain,
                themeBrand = it.themeBrand,
                darkThemeConfig = it.darkThemeConfig,
                useDynamicColor = it.useDynamicColor,
                user = it.user,
                accessToken = it.accessToken,
                expiresAt = it.expiresAt
            )
        )
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

    fun saveDomain(domain: String) {
        viewModelScope.launch { repository.setDomain(domain) }
    }

    private fun saveAuthToken(token: AuthToken) {
        viewModelScope.launch { repository.setAuthToken(token) }
    }

    //private val _loginResult: MutableState<Result<AuthToken>> = mutableStateOf(Result.Success())

    fun login(email: String, password: String, userAgent: String) {
        viewModelScope.launch {
            val response = authRepository.login(email, password, userAgent)

            if (response is Result.Success) {
                saveAuthToken(response.data)
            }
        }
    }

    fun syncUser() {
        viewModelScope.launch {
            val response = repository.me(AppStatus.accessToken)

            if (response is Result.Success) {
                repository.setUser(response.data)
            }
        }
    }
}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val userData: UserData) : MainActivityUiState
}