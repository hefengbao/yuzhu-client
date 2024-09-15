/*
 * Feeder: Android RSS reader app
 * https://gitlab.com/spacecowboy/Feeder
 *
 * Copyright (C) 2022  Jonas Kalderstam
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.hefengbao.yuzhu.ui.component.reader

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hefengbao.yuzhu.common.ext.alphaLN

const val MAX_CONTENT_WIDTH = 840.0

@Stable
@Composable
@ReadOnlyComposable
fun imageHorizontalPadding(): Int = 24

@Stable
@Composable
@ReadOnlyComposable
fun imageShape(): RoundedCornerShape =
    RoundedCornerShape(0.dp)

@Stable
@Composable
@ReadOnlyComposable
fun onSurfaceColor(): Color =
    MaterialTheme.colorScheme.onSurface

@Stable
@Composable
@ReadOnlyComposable
fun onSurfaceVariantColor(): Color =
    MaterialTheme.colorScheme.onSurfaceVariant

@Stable
@Composable
@ReadOnlyComposable
fun textHorizontalPadding(): Int = 0

@Stable
@Composable
@ReadOnlyComposable
fun bodyForeground(): Color = onSurfaceVariantColor()

@Stable
@Composable
@ReadOnlyComposable
fun bodyStyle(): TextStyle = LocalTextStyle.current.merge(
    TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        letterSpacing = 0.5F.sp,
        color = bodyForeground(),
        textAlign = TextAlign.Start,
    )
)

@Stable
@Composable
@ReadOnlyComposable
fun h1Style(): TextStyle = LocalTextStyle.current.merge(
    TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        letterSpacing = 0.sp,
        color = onSurfaceColor(),
        textAlign = TextAlign.Start,
    )
)

@Stable
@Composable
@ReadOnlyComposable
fun h2Style(): TextStyle = LocalTextStyle.current.merge(
    TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        letterSpacing = 0.sp,
        color = onSurfaceColor(),
        textAlign = TextAlign.Start,
    )
)

@Stable
@Composable
@ReadOnlyComposable
fun h3Style(): TextStyle = LocalTextStyle.current.merge(
    TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp,
        letterSpacing = 0.sp,
        color = onSurfaceColor(),
        textAlign = TextAlign.Start,
    )
)

@Stable
@Composable
@ReadOnlyComposable
fun h4Style(): TextStyle = LocalTextStyle.current.merge(
    TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        letterSpacing = 0.sp,
        color = onSurfaceColor(),
        textAlign = TextAlign.Start,
    )
)

@Stable
@Composable
@ReadOnlyComposable
fun h5Style(): TextStyle = LocalTextStyle.current.merge(
    TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        letterSpacing = 0.sp,
        color = onSurfaceColor(),
        textAlign = TextAlign.Start,
    )
)

@Stable
@Composable
@ReadOnlyComposable
fun h6Style(): TextStyle = LocalTextStyle.current.merge(
    TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        letterSpacing = 0.sp,
        color = onSurfaceColor(),
        textAlign = TextAlign.Start,
    )
)

@Stable
@Composable
@ReadOnlyComposable
fun captionStyle(): TextStyle = LocalTextStyle.current.merge(
    MaterialTheme.typography.bodySmall.merge(
        TextStyle(
            fontFamily = FontFamily.Default,
            color = bodyForeground().copy(alpha = 0.6f),
            textAlign = TextAlign.Center,
        )
    )
)

@Stable
@Composable
@ReadOnlyComposable
fun linkTextStyle(): TextStyle = LocalTextStyle.current.merge(
    TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 17.sp,
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline,
    )
)

@Stable
@Composable
fun codeBlockStyle(): TextStyle =
    MaterialTheme.typography.titleSmall.merge(
        SpanStyle(
            color = bodyForeground(),
            fontFamily = FontFamily.Monospace
        )
    )

@Stable
@Composable
fun codeBlockBackground(): Color =
    MaterialTheme.colorScheme.secondary.copy(alpha = (0.dp).alphaLN(weight = 3.2f))

@Stable
@Composable
fun boldStyle(): TextStyle =
    bodyStyle().merge(
        SpanStyle(
            fontWeight = FontWeight.SemiBold,
            color = onSurfaceColor(),
        )
    )

@Stable
@Composable
fun codeInlineStyle(): SpanStyle =
    MaterialTheme.typography.titleSmall.toSpanStyle().merge(
        SpanStyle(
            color = bodyForeground(),
            fontFamily = FontFamily.Monospace,
        )
    )
