package com.cmps312.newshub.repository

import android.content.Context
import com.cmps312.newshub.datasource.NewsHubDao
import com.cmps312.newshub.datasource.NewsHubDatabase
import com.cmps312.newshub.entity.Article
import com.cmps312.newshub.entity.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

class NewsHubRepo(appContext: Context){
    private val newsHubDao by lazy {
        NewsHubDatabase.getDatabase(appContext).newsHubDao()
    }
//    private var articles = mutableListOf<Article>()
//    private var categories = mutableListOf<Category>()
//
//    fun getArticles(context: Context): List<Article> {
////        read from the assets folder
//        val jsonText = context
//            .assets
//            .open("articles.json")
//            .bufferedReader().use { it.readText() }
//
//        articles = Json { ignoreUnknownKeys = true }
//            .decodeFromString(jsonText)
//
//        return articles
//
//    }
//
//    fun getCategories(context: Context): List<Category> {
////        read from the assets folder
//        val jsonText = context
//            .assets
//            .open("categories.json")
//            .bufferedReader().use { it.readText() }
//
//        categories = Json { ignoreUnknownKeys = true }
//            .decodeFromString(jsonText)
//
//        return categories
//
//    }
//
//    fun getCategory(context: Context , categoryId: Int) = getCategories(context).find { it.id == categoryId }
//
//    fun filterArticles(context: Context, categoryId: Int): List<Article> = getArticles(context)
//        .filter { it.categoryId == categoryId }

    //    Below this write all the methods needed for the app to work.


    fun observeArticles(): Flow<List<Article>> = newsHubDao.observeArticles()

     suspend fun addArticle(article: Article) = newsHubDao.addArticle(article)

     suspend fun deleteArticle(article: Article)= newsHubDao.deleteArticle(article)
     suspend fun addCategory(category: Category)= newsHubDao.addCategory(category)
     fun observeCategories(): Flow<List<Category>> = newsHubDao.observeCategories()

     suspend fun deleteCategory(category: Category) = newsHubDao.deleteCategory(category)

     suspend fun getArticleListByCategory(categoryId: Int): Flow<List<Article>> = newsHubDao.getArticleListByCategory(categoryId)
      fun getCategory(id: Int): Flow<Category> = newsHubDao.getCategory(id)




}