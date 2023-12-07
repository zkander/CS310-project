package com.cmps312.newshub.ui.views

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps312.newshub.entity.Article
import com.cmps312.newshub.entity.Category
import com.cmps312.newshub.ui.theme.NewsHubTheme
import com.cmps312.newshub.ui.viewmodel.NewsHubViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewsArticle(
    onAddNewsArticle: (Article) -> Unit,
    newsHubViewModel: NewsHubViewModel
) {


    OutlinedCard(
        //shape = MaterialTheme.shapes.medium,
        shape = RoundedCornerShape(8.dp),
        // modifier = modifier.size(280.dp, 240.dp)
        modifier = Modifier
            .padding(10.dp, 5.dp, 10.dp, 10.dp)
            .fillMaxHeight(),
        //set card elevation of the card
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                "Add News Info",
                fontSize = 30.sp,
                letterSpacing = 3.sp,
            )

            OutlinedTextField(
                value = newsHubViewModel.title,
                onValueChange = { newsHubViewModel.title = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Title") }
            )

            // Article
            OutlinedTextField(
                value = newsHubViewModel.article,
                onValueChange = { newsHubViewModel.article = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Article") },
                maxLines = 10
            )

            // Image
            OutlinedTextField(
                value = newsHubViewModel.image,
                onValueChange = { newsHubViewModel.image = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Image Name") }
            )

            // Category
            CategoryDropDown(
                onSelectedOptionChange = {
                    newsHubViewModel.selectedCategory = it
                },
                newsHubViewModel = newsHubViewModel
            )

            // Author
            OutlinedTextField(
                value = newsHubViewModel.author,
                onValueChange = { newsHubViewModel.author = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Author") }
            )

            // Date
            OutlinedTextField(
                value = newsHubViewModel.date,
                onValueChange = { newsHubViewModel.date = it },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(color = Color.Black),
                label = { Text("Date") }
            )
            Button(
                onClick = {
                    val newArticle =
                        if (newsHubViewModel.selectedArticle.id == -1)
                            Article(
                                title = newsHubViewModel.title,
                                article = newsHubViewModel.article,
                                image = newsHubViewModel.image,
                                categoryId = newsHubViewModel.selectedCategory.id,
                                author = newsHubViewModel.author,
                                date = newsHubViewModel.date
                            )
                        else
                            Article(
                                id = newsHubViewModel.selectedArticle.id,
                                title = newsHubViewModel.title,
                                article = newsHubViewModel.article,
                                image = newsHubViewModel.image,
                                categoryId = newsHubViewModel.selectedCategory.id,
                                author = newsHubViewModel.author,
                                date = newsHubViewModel.date
                            )
                    onAddNewsArticle(newArticle)
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text("Add Article")
            }
        }
    }
}

//@Preview
//@Composable
//fun AddNewsArticle() {
//    val context = LocalContext.current
//    NewsHubTheme {
//        AddNewsArticle() {
//            Toast.makeText(context, it.author, Toast.LENGTH_SHORT).show()
//        }
//    }
//}
