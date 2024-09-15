package com.hefengbao.yuzhu.ui.screen.tag

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hefengbao.yuzhu.ui.component.SimpleScaffold

@Composable
fun TagCreateRoute(
    viewModel: TagCreateViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    TagCreateScreen(
        onBackClick = onBackClick,
        onSave = { viewModel.save(it) }
    )
}

@Composable
fun TagCreateScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSave: (name: String) -> Unit
) {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current
    SimpleScaffold(
        onBackClick = onBackClick,
        title = "创建标签",
        actions = {
            IconButton(
                onClick = {
                    if (name.isNotBlank()) {
                        onSave(name)
                    } else {
                        Toast.makeText(context, "标签名不能为空", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(imageVector = Icons.AutoMirrored.Outlined.Send, contentDescription = null)
            }
        }
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            singleLine = true,
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
    }
}