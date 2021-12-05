package com.shahin.data.network.services


import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.shahin.LaunchProfileQuery
import com.shahin.LaunchRepoListQuery
import javax.inject.Inject


interface GithubService {

    suspend fun getProfile(): Response<LaunchProfileQuery.Data>
    suspend fun getRepoList(): Response<LaunchRepoListQuery.Data>

}

class DefaultGithubService @Inject constructor(
    val apolloClient:ApolloClient
) : GithubService {
    override suspend fun getProfile(): Response<LaunchProfileQuery.Data> =
        apolloClient.query(LaunchProfileQuery()).await()

    override suspend fun getRepoList(): Response<LaunchRepoListQuery.Data> =
        apolloClient.query(LaunchRepoListQuery(5)).await()

}