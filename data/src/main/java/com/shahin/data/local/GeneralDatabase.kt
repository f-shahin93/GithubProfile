package com.shahin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahin.data.local.dao.ProfileDao
import com.shahin.data.local.model.ProfileEntity

@Database(entities = [ProfileEntity::class], version = 1)
abstract class MainDatabase : RoomDatabase() {
    abstract val profileDao: ProfileDao
}