package com.cmps312.newshub.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.newshub.entity.Article
import com.cmps312.newshub.entity.Category
import com.cmps312.newshub.repository.NewsHubRepo
import kotlinx.coroutines.launch

class NewsHubViewModel(appContext: Application) : AndroidViewModel(appContext) {
    private val newsHubRepo by lazy { NewsHubRepo(appContext) }

    //    write the
    var selectedArticle by mutableStateOf(Article())

    var title by mutableStateOf(selectedArticle.title)
    var article by mutableStateOf(selectedArticle.article)
    var image by mutableStateOf(selectedArticle.image)
    var author by mutableStateOf(selectedArticle.author)
    var date by mutableStateOf(selectedArticle.date)
    var selectedCategory by mutableStateOf(Category("NONE"))
    var selectedOption by mutableStateOf(selectedCategory.category)
    var searchQuery by mutableStateOf(Category("NONE"))
    var showIcon by mutableStateOf(false)



    var articlesFlow = newsHubRepo.observeArticles()
    var categoriesFlow = newsHubRepo.observeCategories()
    var selectedArticleCategory by mutableStateOf(Category("NONE"))

    fun addArticle(article: Article) {
        viewModelScope.launch { newsHubRepo.addArticle(article) }

    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch { newsHubRepo.deleteArticle(article) }
    }

    fun addCategory(category: Category) {
        viewModelScope.launch { newsHubRepo.addCategory(category) }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch { newsHubRepo.deleteCategory(category) }
    }

    fun getArticlesListByCategory(categoryId: Int) {
        viewModelScope.launch { articlesFlow=newsHubRepo.getArticleListByCategory(categoryId) }
    }

    fun getCategory(id: Int) = newsHubRepo.getCategory(id)



}