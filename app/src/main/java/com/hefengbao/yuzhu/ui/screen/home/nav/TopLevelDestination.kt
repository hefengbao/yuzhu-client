package com.hefengbao.yuzhu.ui.screen.home.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.automirrored.filled.Feed
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.automirrored.outlined.Feed
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.hefengbao.yuzhu.R
import com.hefengbao.yuzhu.ui.screen.article.nav.ARTICLE_INDEX_ROUTE
import com.hefengbao.yuzhu.ui.screen.me.nav.ME_ROUTE
import com.hefengbao.yuzhu.ui.screen.tweet.nav.TWEET_INDEX_ROUTE


enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
    val route: String
) {
    TWEET(
        selectedIcon = Icons.AutoMirrored.Default.Feed,
        unselectedIcon = Icons.AutoMirrored.Outlined.Feed,
        iconTextId = R.string.tweet,
        titleTextId = R.string.tweet,
        route = TWEET_INDEX_ROUTE
    ),
    ARTICLE(
        selectedIcon = Icons.AutoMirrored.Default.Article,
        unselectedIcon = Icons.AutoMirrored.Outlined.Article,
        iconTextId = R.string.article,
        titleTextId = R.string.article,
        route = ARTICLE_INDEX_ROUTE
    ),
    ME(
        selectedIcon = Icons.Default.Person,
        unselectedIcon = Icons.Outlined.Person,
        iconTextId = R.string.me,
        titleTextId = R.string.me,
        route = ME_ROUTE
    )
}
