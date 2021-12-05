package com.shahin.data.local.dao

import androidx.room.*
import com.shahin.data.local.model.ProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profileEntity: ProfileEntity)

    @Query("SELECT * FROM profile")
    fun getProfile(): Flow<ProfileEntity?>

}