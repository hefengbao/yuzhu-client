package com.hefengbao.yuzhu.ui.screen.me

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hefengbao.yuzhu.data.datastore.AppPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MeViewModel @Inject constructor(
    preference: AppPreference
) : ViewModel() {
    val userData = preference.appStatus.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = null
    )
}