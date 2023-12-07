package com.cmps312.newshub.ui.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.cmps312.newshub.entity.Article
import com.cmps312.newshub.entity.Category
import com.cmps312.newshub.ui.viewmodel.NewsHubViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsListScreen(
    newsHubViewModel: NewsHubViewModel,
    onAddArticle: () -> Unit,
    onDeleteArticle: (Article) -> Unit,
    onEditArticle: (Article) -> Unit
) {

    var articles = newsHubViewModel.articlesFlow


    Scaffold(topBar = {
        TopBar(
            updateArticle = { articles = it },
            newsHubViewModel = newsHubViewModel
        )
    }, floatingActionButton = {
        FloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                Modifier.clickable { onAddArticle() })
        }
    }


    ) {
        newsHubViewModel.getArticlesListByCategory(newsHubViewModel.searchQuery.id)
        var filteredArticles = articles.collectAsState(initial = listOf()).value
        ArticleList(
            articles = filteredArticles,
            PaddingValues(10.dp),
            newsHubViewModel,
            onDeleteArticle = {onDeleteArticle(it)},
            onEditArticle={onEditArticle(it)}
        )
    }

}

@Composable
fun ArticleList(
    articles: List<Article>,
    contentPaddingValues: PaddingValues,
    newsHubViewModel: NewsHubViewModel,
    onDeleteArticle: (Article) -> Unit,
    onEditArticle: (Article) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = contentPaddingValues
    ) {
        items(articles) {
            ArticleCard(
                article = it,
                onDeleteArticle = { onDeleteArticle(it) },
                onEditArticle = { onEditArticle(it) },
                newsHubViewModel
            )
        }
    }
}


@Composable
fun ArticleCard(
    article: Article,
    onDeleteArticle: (Article) -> Unit,
    onEditArticle: (Article) -> Unit,
    newsHubViewModel: NewsHubViewModel
) {

//    get image using a name from resource folder
    val imageId = LocalContext.current.resources.getIdentifier(
        article.image,
        "drawable",
        LocalContext.current.packageName
    )

//    TODO Replace this

    val category = newsHubViewModel.getCategory(article.categoryId).collectAsState(initial = null).value?:Category("NONE")

    OutlinedCard {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(imageId),
                contentDescription = null, // decorative
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineLarge
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Text(text = "by : ${article.author}", fontWeight = FontWeight.ExtraBold)
                    Text(text = article.date, fontWeight = FontWeight.ExtraBold)

                }
                Text(text = article.article)

                Text(
                    text = category!!.category,
                    modifier = Modifier.align(Alignment.End),
                    fontWeight = FontWeight.ExtraBold
                )
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Delete",
                        modifier = Modifier
                            .clickable {
                                newsHubViewModel.selectedArticle=article
                                onEditArticle(article)
                            }
                            .size(30.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete",
                        modifier = Modifier
                            .clickable {
                                onDeleteArticle(article)

                            }
                            .size(30.dp),
                        tint = Color.Red
                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun ArticleListPreview() {
//    val stadiums = NewsHubRepo.getArticles(LocalContext.current)
//    NewsHubTheme {
//        ArticleList(articles = stadiums, PaddingValues(10.dp))
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ArticleCardPreview() {
//    val article = NewsHubRepo.getArticles(LocalContext.current)[0]
//    NewsHubTheme {
//        ArticleCard(article = article, onDeleteArticle = {
//
//        }, onEditArticle = {
//
//        })
//    }
//}
