package com.hefengbao.yuzhu.ui.screen.finance.group

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.model.finance.Category
import com.hefengbao.yuzhu.data.model.finance.Group
import com.hefengbao.yuzhu.data.repository.finance.GroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IndexViewModel @Inject constructor(
    private val repository: GroupRepository
): ViewModel() {

    private val _fetchStatus: MutableStateFlow<Result<List<Group>>?> = MutableStateFlow(null)
    val fetchStatus: SharedFlow<Result<List<Group>>?> = _fetchStatus
    fun fetchCategories(){
        _fetchStatus.value = Result.Loading
        viewModelScope.launch {
            _fetchStatus.value = repository.fetchGroups(AppStatus.accessToken)
        }
    }
}