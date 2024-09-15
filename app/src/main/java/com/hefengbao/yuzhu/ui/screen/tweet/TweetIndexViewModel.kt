package com.hefengbao.yuzhu.ui.screen.tweet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.common.ext.asBearerToken
import com.hefengbao.yuzhu.data.repository.TweetRepository
import com.hefengbao.yuzhu.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetIndexViewModel @Inject constructor(
    repository: TweetRepository,
    private val userDataRepository: UserDataRepository
) : ViewModel() {
    private var authorization: String? = null

    init {
        viewModelScope.launch {
            userDataRepository.getUserData().collectLatest {
                it.accessToken?.let { token ->
                    authorization = token.asBearerToken()
                }
            }
        }
    }

    val tweets = repository.getTweets(AppStatus.accessToken).cachedIn(viewModelScope)
}