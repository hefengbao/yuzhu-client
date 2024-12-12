package com.hefengbao.yuzhu.ui.screen.post.tweet

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.enums.post.Commentable
import com.hefengbao.yuzhu.data.model.post.Post
import com.hefengbao.yuzhu.data.model.post.Tag
import com.hefengbao.yuzhu.data.model.post.asPostEntity
import com.hefengbao.yuzhu.data.repository.post.TweetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TweetCreateViewModel @Inject constructor(
    private val repository: TweetRepository
) : ViewModel() {
    val tags = mutableStateListOf<Tag>()

    fun addTag(entity: Tag) {
        tags.add(entity)
    }

    fun deleteTag(index: Int) {
        tags.removeAt(index)
    }

    private val _tweetResult: MutableStateFlow<Result<Post>?> = MutableStateFlow(null)
    val tweetResult: SharedFlow<Result<Post>?> = _tweetResult

    fun save(body: String, commentable: Commentable, tags: List<Tag>) {
        _tweetResult.value = Result.Loading
        viewModelScope.launch {
            val response = repository.create(AppStatus.accessToken, body, commentable.status, tags)

            if (response is Result.Success) {
                repository.insert(response.data.asPostEntity())
            }

            _tweetResult.value = response
        }
    }
}