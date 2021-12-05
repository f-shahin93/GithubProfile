package com.shahin.data.repository

import com.shahin.data.local.dao.ProfileDao
import com.shahin.data.local.dao.RepositoryListDao
import com.shahin.data.local.model.RepoEntity
import com.shahin.data.model.DataResult
import com.shahin.data.model.Profile
import com.shahin.data.model.RepoItem
import com.shahin.data.network.services.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

interface GithubRepository {

    fun getProfile(): Flow<DataResult<Profile>>
    fun getRepoList(): Flow<DataResult<List<RepoItem>>>

}

class DefaultGithubRepository @Inject constructor(
    private val profileDao: ProfileDao,
    private val repoDao: RepositoryListDao,
    private val githubService: GithubService
) : GithubRepository {

    override fun getProfile() = flow {
        emit(DataResult.Loading())

        try {
            withTimeout(3000) {
                getProfileLocal()
            }
        } catch (timeEx: TimeoutCancellationException) {

        } catch (ex: Exception) {
            emit(DataResult.Error(ex))
        }

        val remoteResult = githubService.getProfile()
        remoteResult.data?.let { data -> profileDao.insert(data.toEntity()) }
        getProfileLocal()

    }.catch { ex -> emit(DataResult.Error(ex)) }
        .flowOn(Dispatchers.IO)


    private suspend fun FlowCollector<DataResult<Profile>>.getProfileLocal() {
        profileDao.getProfile().collect {
            it?.let {
                emit(DataResult.Loading(it.toDomain()))
                emit(DataResult.Success(it.toDomain()))
            }
        }
    }


    override fun getRepoList() = flow {
        emit(DataResult.Loading())

        try {
            withTimeout(3000) {
                getRepoListLocal()
            }
        } catch (timeEx: TimeoutCancellationException) {

        } catch (ex: Exception) {
            emit(DataResult.Error(ex))
        }

        val remoteResult = githubService.getRepoList()
        remoteResult.data?.viewer?.repositories?.nodes?.let { data ->
            val localResult = mutableListOf<RepoEntity>()
            data.map { it?.let { item -> localResult.add(item.toEntity()) } }
            repoDao.insert(localResult)
        }

        getRepoListLocal()

    }.catch { ex -> emit(DataResult.Error(ex)) }
        .flowOn(Dispatchers.IO)

    private suspend fun FlowCollector<DataResult<List<RepoItem>>>.getRepoListLocal() {
        repoDao.getRepoList().collect {
            it?.let {
                emit(DataResult.Loading(it.map { item -> item.toDomain() }))
                emit(DataResult.Success(it.map { item -> item.toDomain() }))
            }
        }
    }


}