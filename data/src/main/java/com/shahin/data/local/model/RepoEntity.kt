package com.shahin.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "repo")
data class RepoEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name="createdAt")
    val createdAt: String,
    @ColumnInfo(name="description")
    val description: String?,
)