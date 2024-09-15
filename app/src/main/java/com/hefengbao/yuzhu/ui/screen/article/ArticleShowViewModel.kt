package com.hefengbao.yuzhu.ui.screen.article

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.common.storage.AndroidImageDownloader
import com.hefengbao.yuzhu.data.repository.ArticleRepository
import com.hefengbao.yuzhu.ui.screen.article.nav.ArticleShowArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleShowViewModel @Inject constructor(
    repository: ArticleRepository,
    private val imageDownloader: AndroidImageDownloader,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val args = ArticleShowArgs(savedStateHandle)

    val article = repository.getArticle(args.articleId.toInt()).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )

    val comments = repository.getComments(AppStatus.accessToken, args.articleId.toInt()).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = PagingData.empty()
    )

    fun downloadImage(
        url: String,
        onSuccess: (Uri) -> Unit = {},
        onFailure: (Throwable) -> Unit = {}
    ) {
        viewModelScope.launch {
            imageDownloader.downloadImage(url).onSuccess(onSuccess).onFailure(onFailure)
        }
    }
}