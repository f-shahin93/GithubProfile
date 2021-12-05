package com.shahin.data.di

import com.apollographql.apollo.ApolloClient
import com.shahin.data.network.interceptors.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [UrlModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(authorizationInterceptor: AuthorizationInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Provides
    @Singleton
    fun provideApolloClient(client: OkHttpClient, @Named("baseUrl") baseUrl: String): ApolloClient =
        ApolloClient.builder()
            .okHttpClient(client)
            .serverUrl(baseUrl)
            .build()

}