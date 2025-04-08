package com.hefengbao.yuzhu.ui.screen.finance.account

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.model.finance.Account
import com.hefengbao.yuzhu.data.model.finance.asAccountEntity
import com.hefengbao.yuzhu.data.repository.finance.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IndexViewModel @Inject constructor(
    private val repository: AccountRepository
): ViewModel() {
    private val _fetchStatus: MutableStateFlow<Result<List<Account>>?> = MutableStateFlow(null)
    val fetchStatus: SharedFlow<Result<List<Account>>?> = _fetchStatus
    fun fetchAccounts(){
        _fetchStatus.value = Result.Loading
        viewModelScope.launch {
            _fetchStatus.value  = repository.fetchAccounts(AppStatus.accessToken)
        }
    }

    fun insertAccounts(accounts: List<Account>){
        viewModelScope.launch { repository.insertAll(accounts.map { it.asAccountEntity() }) }
    }

    val accounts = repository.getAccounts().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
}