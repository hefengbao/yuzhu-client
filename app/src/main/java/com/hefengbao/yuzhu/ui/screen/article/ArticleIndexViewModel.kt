package com.hefengbao.yuzhu.ui.screen.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.data.repository.ArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleIndexViewModel @Inject constructor(
    repository: ArticleRepository,
) : ViewModel() {
    val articles = repository.getArticles(AppStatus.accessToken).cachedIn(viewModelScope)
}