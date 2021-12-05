package com.shahin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahin.data.local.dao.ProfileDao
import com.shahin.data.local.dao.RepositoryListDao
import com.shahin.data.local.model.ProfileEntity
import com.shahin.data.local.model.RepoEntity

@Database(entities = [ProfileEntity::class, RepoEntity::class], version = 1)
abstract class MainDatabase : RoomDatabase() {
    abstract val profileDao: ProfileDao
    abstract val repositoryListDao: RepositoryListDao
}