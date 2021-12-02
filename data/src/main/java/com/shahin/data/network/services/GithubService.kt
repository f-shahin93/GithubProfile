package com.shahin.data.network.services

import retrofit2.http.GET


interface GithubService {

    @GET()
    fun getData()

}