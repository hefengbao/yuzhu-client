package com.hefengbao.yuzhu.ui.screen.settings.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsTitle(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, 8.dp),
        text = title,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = .5f),
    )
}