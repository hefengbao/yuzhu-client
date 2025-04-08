package com.hefengbao.yuzhu.ui.screen.finance.transaction

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.hefengbao.yuzhu.data.enums.finance.FinanceType
import com.hefengbao.yuzhu.data.model.finance.Account
import com.hefengbao.yuzhu.data.model.finance.Category
import com.hefengbao.yuzhu.ui.component.SimpleScaffold
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.toLocalDateTime

@OptIn(FormatStringsInDatetimeFormats::class)
@Composable
fun CreateRoute(
    viewModel: CreateViewModel = hiltViewModel(),
    backStackEntry: NavBackStackEntry,
    onBackClick: () -> Unit,
    onSelectAccountClick: () -> Unit,
    onSelectCategoryClick: (type: String) -> Unit,
) {
    val accountState = rememberSaveable { mutableStateOf<Account?>(null) }
    val amountState = rememberSaveable { mutableDoubleStateOf(0.0) }
    val categoryState = rememberSaveable { mutableStateOf<Category?>(null) }
    val dateState = rememberSaveable { mutableLongStateOf(Clock.System.now().toEpochMilliseconds()) }
    val typeState = rememberSaveable { mutableStateOf(FinanceType.Expense.type) }
    val notesState = rememberSaveable { mutableStateOf<String?>(null) }

    val savedStateHandle = backStackEntry.savedStateHandle

    if (savedStateHandle.contains("account")){
        savedStateHandle.get<Account>("account")?.let {
            accountState.value = it
        }
        savedStateHandle.remove<Account>("account")
    }

    if (savedStateHandle.contains("category")){
        savedStateHandle.get<Category>("category")?.let {
            categoryState.value = it
        }
        savedStateHandle.remove<Category>("category")
    }

    CreateScreen(
        onBackClick = onBackClick,
        onSubmitClick = {
            viewModel.create(
                account = accountState.value!!,
                date = Instant.fromEpochMilliseconds(dateState.longValue)
                    .format(DateTimeComponents.Format { byUnicodePattern("yyyy-MM-dd") }),
                type = typeState.value,
                category = categoryState.value!!,
                amount = amountState.doubleValue,
                notes = notesState.value?.trim()
            )
        },
        accountState = accountState,
        amountState = amountState,
        categoryState = categoryState,
        dateState = dateState,
        typeState = typeState,
        notesState = notesState,
        onSelectAccountClick = onSelectAccountClick,
        onSelectCategoryClick = { onSelectCategoryClick(typeState.value) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSubmitClick: () -> Unit,
    accountState: MutableState<Account?>,
    amountState: MutableDoubleState,
    categoryState: MutableState<Category?>,
    dateState: MutableLongState,
    typeState: MutableState<String>,
    notesState: MutableState<String?>,
    onSelectAccountClick: () -> Unit,
    onSelectCategoryClick: () -> Unit,
) {
    var shouldShowTypeSelector by rememberSaveable { mutableStateOf(false) }
    var shouldShowDatePicker by rememberSaveable { mutableStateOf(false) }

    val focusRequesterForAccount = remember { FocusRequester() }
    val focusRequesterForCategory = remember { FocusRequester() }
    val focusRequesterForDate = remember { FocusRequester() }
    val focusRequesterForType = remember { FocusRequester() }

    SimpleScaffold(
        onBackClick = onBackClick,
        title = "记账",
        actions = {
            IconButton(
                onClick = onSubmitClick,
                enabled = accountState.value != null &&
                        amountState.doubleValue > 0.0 &&
                        categoryState.value != null
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.Send, contentDescription = "提交")
            }
        }
    ){
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .focusRequester(focusRequesterForAccount)
                    .onFocusChanged { event ->
                        if (event.isFocused){
                            onSelectAccountClick()
                            focusRequesterForAccount.freeFocus()
                        }
                    },
                label = {
                    Text("账户*")
                },
                value = accountState.value?.name ?: "",
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = "")
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .focusRequester(focusRequesterForDate)
                    .onFocusChanged { event ->
                        if (event.isFocused){
                            shouldShowDatePicker = true
                            focusRequesterForDate.freeFocus()
                        }
                    },
                label = {
                    Text("日期*")
                },
                value = Instant.fromEpochMilliseconds(dateState.longValue).toLocalDateTime(TimeZone.currentSystemDefault()).date.toString(),
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.CalendarToday, contentDescription = "")
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .focusRequester(focusRequesterForType)
                    .onFocusEvent { event ->
                        if (event.isFocused){
                            shouldShowTypeSelector = true
                            focusRequesterForType.freeFocus()
                        }
                    },
                label = {
                    Text("类别*")
                },
                value = FinanceType.label(typeState.value),
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = "")
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .focusRequester(focusRequesterForCategory)
                    .onFocusChanged { event ->
                        if (event.isFocused){
                            onSelectCategoryClick()
                            focusRequesterForCategory.freeFocus()
                        }
                    },
                label = {
                    Text("类型*")
                },
                value = categoryState.value?.name ?: "",
                onValueChange = {},
                readOnly = true,
                singleLine = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.ArrowDropDown, contentDescription = "")
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("费用*")
                },
                value = if (amountState.doubleValue == 0.0)  "" else amountState.doubleValue.toString(),
                onValueChange = { amountState.doubleValue = it.toDouble() },
                singleLine = true,
                trailingIcon = {
                    Text("￥")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("备注")
                },
                value = notesState.value ?: "",
                onValueChange = { notesState.value = it},
                minLines = 2,
            )
        }
    }

    if (shouldShowTypeSelector){
        Dialog(onDismissRequest = { shouldShowTypeSelector = false}) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        text = "请选择",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium
                    )
                    FinanceType.entries.map {
                        Text(
                            modifier = modifier.fillMaxWidth()
                                .clickable {
                                    typeState.value = it.type

                                    // 重置 Category
                                    categoryState.value = null

                                    shouldShowTypeSelector = false
                                }
                                .padding(vertical = 16.dp),
                            text = FinanceType.label(it.type),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }

    if (shouldShowDatePicker){
        val datePickerState = rememberDatePickerState()
        DatePickerDialog(
            onDismissRequest = { shouldShowDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let { dateState.longValue = it }
                    shouldShowDatePicker = false
                }) {
                    Text("确认")
                }
            },
            dismissButton = {
                TextButton(onClick = { }) {
                    Text("取消")
                }
            }
        ){
            DatePicker(state = datePickerState)
        }
    }
}