package com.hefengbao.yuzhu.ui.screen.finance.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.yuzhu.AppStatus
import com.hefengbao.yuzhu.common.network.Result
import com.hefengbao.yuzhu.data.model.finance.Account
import com.hefengbao.yuzhu.data.model.finance.Category
import com.hefengbao.yuzhu.data.model.finance.Transaction
import com.hefengbao.yuzhu.data.repository.finance.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val repository: TransactionRepository
): ViewModel() {
    private val _status: MutableStateFlow<Result<Transaction>?> = MutableStateFlow(null)
    val status: SharedFlow<Result<Transaction>?>  =_status

    fun create(
        account: Account,
        date: String,
        type: String,
        category: Category,
        amount: Double,
        notes: String?
    ){
        viewModelScope.launch {
            _status.value = Result.Loading
            _status.value = repository.createTransaction(
                authorization = AppStatus.accessToken,
                accountId = account.id,
                date = date,
                type = type,
                categoryId = category.id,
                amount = amount,
                notes = notes
            )
        }
    }
}