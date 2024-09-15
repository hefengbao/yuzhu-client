package com.hefengbao.yuzhu.ui.screen.settings

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hefengbao.yuzhu.R
import com.hefengbao.yuzhu.common.util.ClipboardUtil
import com.hefengbao.yuzhu.common.util.SystemUtil
import com.hefengbao.yuzhu.ui.component.SimpleScaffold

@Composable
fun AboutRoute(
    onBackClick: () -> Unit,
) {
    val context = LocalContext.current
    val version = SystemUtil.getVersionName(context)

    AboutScreen(
        onBackClick = onBackClick,
        context = context,
        version = version
    )
}

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    context: Context,
    version: String?
) {
    SimpleScaffold(onBackClick = onBackClick, title = "关于") {
        SelectionContainer {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Column(
                        modifier = modifier.align(Alignment.Center),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_logo),
                            contentDescription = null,
                            modifier = modifier
                                .size(108.dp)
                        )
                        Text(
                            text = "当前版本：$version",
                            style = MaterialTheme.typography.labelSmall,
                            textAlign = TextAlign.Center,
                            modifier = modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }

                Text(text = "❤贺丰宝（hefengbao）设计和编码❤")

                val desc = """
                源码仓库：
                
                https://github.com/hefengbao/yuzhu
                
                https://github.com/hefengbao/yuzhu-client
                
                或者
                
                https://gitee.com/hefengbao/yuzhu
                
                https://gitee.com/hefengbao/yuzhu-client
            """.trimIndent()

                Text(text = desc)

                val copyText = "NowInLife"

                Row(
                    modifier = modifier.padding(bottom = 48.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "公众号：$copyText")
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = null,
                        modifier = modifier
                            .clickable {
                                ClipboardUtil.textCopyThenPost(context, copyText)
                            }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AboutScreenPreview() {
    AboutRoute(
        onBackClick = {}
    )
}