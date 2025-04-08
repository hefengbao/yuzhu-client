package com.hefengbao.yuzhu.ui.screen.finance.category

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.database.entity.finance.CategoryEntity
import com.hefengbao.yuzhu.data.model.finance.Category
import com.hefengbao.yuzhu.data.model.finance.Group
import com.hefengbao.yuzhu.data.model.finance.asCategoryEntity
import com.hefengbao.yuzhu.data.model.finance.asGroupEntity
import com.hefengbao.yuzhu.data.repository.finance.CategoryRepository
import com.hefengbao.yuzhu.data.repository.finance.GroupRepository
import com.hefengbao.yuzhu.ui.screen.finance.category.nav.FinanceCategoriesArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IndexViewModel @Inject constructor(
    private val repository: CategoryRepository,
    private val groupRepository: GroupRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val args = FinanceCategoriesArgs(savedStateHandle)

    private val _fetchStatus: MutableStateFlow<Result<List<Category>>?> = MutableStateFlow(null)
    val fetchStatus: SharedFlow<Result<List<Category>>?>  = _fetchStatus
    fun fetchCategories(){
        _fetchStatus.value = Result.Loading
        viewModelScope.launch {
            _fetchStatus.value = repository.fetchCategories(AppStatus.accessToken)
        }
    }

    private val _fetchGroupsStatus: MutableStateFlow<Result<List<Group>>?> = MutableStateFlow(null)
    val fetchGroupsStatus: SharedFlow<Result<List<Group>>?> = _fetchGroupsStatus
    fun fetchGroups(){
        _fetchGroupsStatus.value = Result.Loading
        viewModelScope.launch {
            _fetchGroupsStatus.value = groupRepository.fetchGroups(AppStatus.accessToken)
        }
    }


    fun insertGroups(groups: List<Group>){
        viewModelScope.launch {
            groupRepository.clear()
            groupRepository.insertAll(groups.map { it.asGroupEntity() })
        }
    }

    fun insertCategories(categories: List<Category>){
        viewModelScope.launch {
            repository.clear()
            repository.insertAll(categories.map { it.asCategoryEntity() })
        }
    }

    val groups = groupRepository.getGroups(args.type)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )


    private val _categories: MutableStateFlow<List<CategoryEntity>> = MutableStateFlow(emptyList())
    val categories: SharedFlow<List<CategoryEntity>> = _categories

    fun getCategories(ids: IntArray){
        viewModelScope.launch {
            repository.getCategories(ids).collectLatest {
                _categories.value = it
            }
        }
    }
}