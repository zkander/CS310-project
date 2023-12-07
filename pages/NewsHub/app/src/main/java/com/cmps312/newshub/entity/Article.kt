package com.cmps312.newshub.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
@Entity(foreignKeys = [ForeignKey(
    entity = Category::class,
    parentColumns = ["id"],
    childColumns = ["categoryId"],
    onDelete = ForeignKey.CASCADE,
    onUpdate = ForeignKey.CASCADE,
)])
@Serializable
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var title: String="",
    var article: String="",
    var image: String="",
    var categoryId: Int=-1,
    var author: String="",
    var date: String=""
)


