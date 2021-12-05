package com.shahin.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "login")
    val login: String?,
    @ColumnInfo(name = "avatarUrl")
    val avatarUrl: String?,
    @ColumnInfo(name = "url")
    val url: String?,
)