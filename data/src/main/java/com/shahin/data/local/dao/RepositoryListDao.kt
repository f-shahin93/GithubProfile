package com.shahin.data.local.dao

import androidx.room.*
import com.shahin.data.local.model.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RepositoryListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(list: List<RepoEntity>)

    @Query("SELECT * FROM repo")
    abstract fun getRepoList(): Flow<List<RepoEntity>?>

    @Query("DELETE FROM repo")
    protected abstract fun deleteAll()

}