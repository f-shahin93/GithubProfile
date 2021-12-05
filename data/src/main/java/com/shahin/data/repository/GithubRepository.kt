package com.shahin.data.repository

import com.shahin.data.local.dao.ProfileDao
import com.shahin.data.model.DataResult
import com.shahin.data.model.Profile
import com.shahin.data.network.services.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

interface GithubRepository {

    fun getProfile(): Flow<DataResult<Profile>>
    fun getRepoList(): Flow<DataResult<Boolean>>

}

class DefaultGithubRepository @Inject constructor(
    private val profileDao: ProfileDao,
    private val githubService: GithubService
) : GithubRepository {

    override fun getProfile() = flow {
        emit(DataResult.Loading())

        try {
            withTimeout(3000) {
                profileDao.getProfile().collect {
                    it?.let {
                        emit(DataResult.Loading(it.toDomain()))
                        emit(DataResult.Success(it.toDomain()))
                    }
                }
            }
        } catch (timeEx:TimeoutCancellationException) {

        } catch (ex: Exception) {
            emit(DataResult.Error(ex))
        }

        val remoteResult = githubService.getProfile()
        remoteResult.data?.let { data -> profileDao.insert(data.toEntity()) }

        remoteResult.data?.let { data ->
            emit(DataResult.Success(data.toEntity().toDomain()))
        }
        emit(DataResult.Loading())

    }.catch { ex -> emit(DataResult.Error(ex)) }
        .flowOn(Dispatchers.IO)

    override fun getRepoList() = flow {
        emit(DataResult.Loading())

        /*try {
           withTimeout(3000) {
                profileDao.getProfile().collect {
                    it?.let {
                        emit(DataResult.Loading(it.toDomain()))
                        emit(DataResult.Success(it.toDomain()))
                    }
                }
            }
        } catch (ex: Exception) {
            emit(DataResult.Error(ex))
        }*/

        val remoteResult = githubService.getRepoList()

        remoteResult.data?.let { data ->
            emit(DataResult.Success(true))
        }
        emit(DataResult.Loading())

    }.catch { ex ->
        emit(DataResult.Error(ex))
    }.flowOn(Dispatchers.IO)


}