package com.example.spotifyeminenceinnovationtask.album.di

import com.example.spotifyeminenceinnovationtask.BuildConfig
import com.example.spotifyeminenceinnovationtask.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SpotifyBaseUrl
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApiHeaders
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val CONNECTION_TIME_OUT_SECONDS : Long = 30

    @Provides
    @Singleton
    fun providesGsonConverter() : GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        val logLevel = HttpLoggingInterceptor.Level.BODY
        return HttpLoggingInterceptor().setLevel(logLevel)
    }

    @Provides
    @ApiKey
    fun provideApiKey() : String = BuildConfig.API_KEY

    @Provides
    @ApiHeaders
    fun providesHeaders(@ApiKey apiKey: String) : Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(Constants.ApiHeaders.ApiKey,apiKey).build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApiHeaders headers : Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headers)
            .readTimeout(CONNECTION_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(CONNECTION_TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @SpotifyBaseUrl
    fun provideBaseUrl() : String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(
        @SpotifyBaseUrl baseUrl : String,
        gsonConverterFactory: GsonConverterFactory,
        client: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }
}