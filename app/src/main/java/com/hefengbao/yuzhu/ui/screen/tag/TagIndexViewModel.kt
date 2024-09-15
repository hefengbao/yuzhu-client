package com.hefengbao.yuzhu.ui.screen.tag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.model.asTagEntity
import com.hefengbao.yuzhu.data.repository.TagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TagIndexViewModel @Inject constructor(
    private val repository: TagRepository
) : ViewModel() {
    val tags = repository.getTags().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun sync() {
        viewModelScope.launch {
            val response = repository.syncTags(AppStatus.accessToken)

            if (response is Result.Success) {
                repository.insertAll(response.data.map { it.asTagEntity() })
            }
        }
    }
}