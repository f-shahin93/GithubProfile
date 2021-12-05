package com.shahin.data.di

import com.shahin.data.network.services.DefaultGithubService
import com.shahin.data.network.services.GithubService
import com.shahin.data.repository.DefaultGithubRepository
import com.shahin.data.repository.GithubRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindGithubRepository(repository: DefaultGithubRepository): GithubRepository

    @Binds
    @Singleton
    abstract fun bindGithubService(service: DefaultGithubService): GithubService

}