package com.sparrowhawkmobile.jamdemo.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class GithubApiManager {

    companion object {
        private const val apiUrl = "https://api.github.com/"

        val INSTANCE: GithubApiManager by lazy { GithubApiManager() }
    }

    fun createRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(createConverter())
            .build()
    }

    private fun createConverter(): Converter.Factory {
        return JacksonConverterFactory.create(
            ObjectMapper()
                .registerKotlinModule()
        )
    }

    fun getGithubApiClient() = createRetrofit().create(GithubApi::class.java)
}