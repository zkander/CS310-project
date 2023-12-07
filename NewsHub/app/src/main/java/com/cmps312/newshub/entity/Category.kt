package com.cmps312.newshub.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
@Entity
@Serializable
data class Category (
    val category: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)