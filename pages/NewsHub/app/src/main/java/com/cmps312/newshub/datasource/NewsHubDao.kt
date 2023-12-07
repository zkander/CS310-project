package com.cmps312.newshub.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.cmps312.newshub.entity.Article
import com.cmps312.newshub.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsHubDao {
    //    add the Room annotations
    @Query("SELECT * FROM Article ")
    fun observeArticles(): Flow<List<Article>>

    @Upsert
    suspend fun addArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(category: Category)

    @Query("SELECT * FROM Category")
    fun observeCategories(): Flow<List<Category>>

    @Delete
    suspend fun deleteCategory(category: Category)

    @Query("SELECT * FROM Article WHERE categoryId=:categoryId")
     fun getArticleListByCategory(categoryId: Int): Flow<List<Article>>

    @Query("SELECT * FROM Category WHERE id=:id")
      fun getCategory(id: Int): Flow<Category>

}