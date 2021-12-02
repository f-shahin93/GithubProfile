package com.shahin.data.di

import android.content.Context
import androidx.room.Room
import com.shahin.data.local.MainDatabase
import com.shahin.data.local.dao.ProfileDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(context: Context):MainDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            MainDatabase::class.java,
            "db"
        ).build()


    @Provides
    @Singleton
    fun provideProfileDao(generalDatabase: MainDatabase): ProfileDao =
        generalDatabase.profileDao

}