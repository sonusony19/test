package com.st.wte.koin

import com.google.gson.GsonBuilder
import com.st.wte.network.ApiService
import com.st.wte.network.NetworkManager
import com.st.wte.repositories.Repository
import com.st.wte.utils.URLs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
    }

    single {
        val client = Retrofit.Builder().baseUrl(URLs.BASE_URL).client(get())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .addConverterFactory(GsonConverterFactory.create()).build()
        client.create(ApiService::class.java)
    }

    factory { NetworkManager(get()) }

    factory { Repository() }
}