package com.lessons.filmsmvp.data.di.modules


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lessons.filmsmvp.data.api.FilmsApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

const val API_KEY: String = com.lessons.filmsmvp.BuildConfig.FILMS_API_KEY
const val LANG_RU = "ru-RU"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342/"

@Module
class FilmsApiModule {

    @Reusable
    @Provides
    fun provideGitHubApi(): FilmsApi =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(createOkHttpClient(ApiInterceptor()))
            .build()
            .create(FilmsApi::class.java)

    private val gson: Gson =
        GsonBuilder()
            .create()

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }

    class ApiInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val request = chain.request()
            val url = request.url.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .addQueryParameter("language", LANG_RU)
                .build()
            val newRequest = request.newBuilder().url(url).build()
            return chain.proceed(newRequest)
        }
    }

}