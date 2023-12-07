package com.cmps312.newshub.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cmps312.newshub.entity.Article
import com.cmps312.newshub.entity.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json


@Database(entities = [Article::class, Category::class], version = 7, exportSchema = false)
abstract class NewsHubDatabase: RoomDatabase() {
    abstract fun newsHubDao(): NewsHubDao

    companion object {
        private var database: NewsHubDatabase? = null

        fun getDatabase(context: Context): NewsHubDatabase {
            if (database == null) {
                database = Room.databaseBuilder(
                    context.applicationContext,
                    NewsHubDatabase::class.java,
                    "NewsHubDatabase_db"
                ).fallbackToDestructiveMigration().build()
            }
            return database as NewsHubDatabase
        }
        fun initializeData(context: Context) {
            CoroutineScope(Dispatchers.IO).launch {
                val db = getDatabase(context)
                populateDatabase(db, context)
            }
        }

        private class DatabaseCallback(private val context: Context) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(getDatabase(context), context)
                }
            }
        }

        suspend fun populateDatabase(db: NewsHubDatabase, context: Context) {
            val categories = getCategories(context)
            db.newsHubDao().apply {
                categories.forEach { addCategory(it) }
            }
            val articles = getArticles(context)
            db.newsHubDao().apply {
                articles.forEach { addArticle(it) }
            }

    }
        fun getCategories(context: Context): List<Category> {

        var categories= mutableListOf<Category>()
            val jsonText = context
                .assets
                .open("categories.json")
                .bufferedReader().use { it.readText() }

            categories = Json { ignoreUnknownKeys = true }
                .decodeFromString(jsonText)

            return categories

        }


        fun getArticles(context: Context): List<Article> {
            var articles= mutableListOf<Article>()

            val jsonText = context
                .assets
                .open("articles.json")
                .bufferedReader().use { it.readText() }

            articles = Json { ignoreUnknownKeys = true }
                .decodeFromString(jsonText)

            return articles

        }
}}