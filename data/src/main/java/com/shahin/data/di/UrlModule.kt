package com.shahin.data.di

import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UrlModule {

    companion object {
        const val WEBSITE_ENDPOINT = "https://api.github.com/graphql"
        const val BASE_PATH_API_URL = ""
    }

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = WEBSITE_ENDPOINT

}