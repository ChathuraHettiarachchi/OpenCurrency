package com.choota.opencurrency.di

import android.app.Application
import androidx.room.Room
import com.choota.opencurrency.common.Constants.BASE_URL
import com.choota.opencurrency.common.Constants.DATABASE
import com.choota.opencurrency.data.local.OpenCurrencyDatabase
import com.choota.opencurrency.data.remote.OpenCurrencyAPI
import com.choota.opencurrency.data.repository.remote.OpenCurrencyRepositoryImpl
import com.choota.opencurrency.domain.repository.remote.OpenCurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * DI module for the application
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * OkHttpClient from connection timeouts and logs
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * Retrofit instance for the application
     */
    @Provides
    @Singleton
    fun providesDMotionAPI(client: OkHttpClient): OpenCurrencyAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenCurrencyAPI::class.java)
    }

    /**
     * Repository that will create an instance of impl of the repo.
     * Using this to call the APIs
     */
    @Provides
    @Singleton
    fun providesRepository(api: OpenCurrencyAPI): OpenCurrencyRepository {
        return OpenCurrencyRepositoryImpl(api)
    }

    /**
     * Provides db for the application
     */
    @Provides
    @Singleton
    fun providesDatabase(app: Application) = Room.databaseBuilder(
        app,
        OpenCurrencyDatabase::class.java,
        DATABASE
    ).build()

    /**
     * Provides ResourceVideo repo for access
     */
//    @Provides
//    @Singleton
//    fun providesCurrencyRepo(db: OpenCurrencyDatabase): ResourceVideoRepository {
//        return ResourceVideoRepositoryImpl(db.resourceVideoDao)
//    }
}