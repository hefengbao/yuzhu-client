package com.hefengbao.yuzhu.ui.screen.tweet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.model.Comment
import com.hefengbao.yuzhu.data.model.asCommentEntity
import com.hefengbao.yuzhu.data.repository.TweetRepository
import com.hefengbao.yuzhu.ui.screen.tweet.nav.TweetShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetShowViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TweetRepository,
) : ViewModel() {
    private val args = TweetShowArgs(savedStateHandle)

    val tweet = repository.getTweet(args.tweetId.toInt())

    val comments = repository.getComments(AppStatus.accessToken, args.tweetId.toInt()).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PagingData.empty()
    )

    private val _commentResult: MutableStateFlow<Result<Comment>?> = MutableStateFlow(null)
    val commentResult: SharedFlow<Result<Comment>?> = _commentResult

    fun comment(tweetId: Int, body: String, parentId: Int? = null) {
        _commentResult.value = Result.Loading
        viewModelScope.launch {
            val response = repository.createComments(AppStatus.accessToken, tweetId, body, parentId)

            if (response is Result.Success) {
                repository.insertComment(response.data.asCommentEntity())
            }

            _commentResult.value = response
        }
    }
}