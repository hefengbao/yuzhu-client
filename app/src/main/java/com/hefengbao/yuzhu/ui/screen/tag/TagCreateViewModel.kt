package com.hefengbao.yuzhu.ui.screen.tag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.model.asTagEntity
import com.hefengbao.yuzhu.data.repository.TagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TagCreateViewModel @Inject constructor(
    private val repository: TagRepository
) : ViewModel() {
    fun save(name: String) {
        viewModelScope.launch {
            val response = repository.create(AppStatus.accessToken, name)

            if (response is Result.Success) {
                repository.insert(response.data.asTagEntity())
            }
        }
    }
}