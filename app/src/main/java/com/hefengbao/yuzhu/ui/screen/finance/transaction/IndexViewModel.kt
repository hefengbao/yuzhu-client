package com.hefengbao.yuzhu.ui.screen.finance.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.data.repository.finance.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IndexViewModel @Inject constructor(
    private val repository: TransactionRepository,
): ViewModel() {

    fun fetchTransactions(startDate: String, endDate: String){
        viewModelScope.launch {
            val data = repository.fetchTransactions(AppStatus.accessToken, startDate, endDate)
        }
    }
}