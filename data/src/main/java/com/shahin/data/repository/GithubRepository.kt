package com.shahin.data.repository

import com.shahin.data.local.dao.ProfileDao
import com.shahin.data.network.services.GithubService
import javax.inject.Inject

interface GithubRepository {

    fun getData()

}

class DefaultGithubRepository @Inject constructor(
    private val profileDao: ProfileDao,
    private val githubService: GithubService
) : GithubRepository{

    override fun getData() {}

}