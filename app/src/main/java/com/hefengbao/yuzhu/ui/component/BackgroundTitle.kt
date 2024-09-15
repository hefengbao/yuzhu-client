package com.hefengbao.yuzhu.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.primary)
            .padding(16.dp, 8.dp),
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.titleSmall
    )
}