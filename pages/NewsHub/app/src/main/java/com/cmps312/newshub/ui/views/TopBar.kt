package com.cmps312.newshub.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.cmps312.newshub.entity.Article
import com.cmps312.newshub.entity.Category
import com.cmps312.newshub.repository.NewsHubRepo
import com.cmps312.newshub.ui.viewmodel.NewsHubViewModel
import kotlinx.coroutines.flow.Flow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    updateArticle: (Flow<List<Article>>) -> Unit,
    newsHubViewModel: NewsHubViewModel,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current
    Column(
        modifier = modifier
            .padding(2.dp)
            .background(Color.LightGray)
            .clip(RectangleShape),
    ) {
        CategoryDropDown({ category ->
            if (category.category == "NONE")
                updateArticle(newsHubViewModel.articlesFlow)
            else
                newsHubViewModel.getArticlesListByCategory(category.id)
            updateArticle(newsHubViewModel.articlesFlow)
        }, newsHubViewModel = newsHubViewModel)
    }
}