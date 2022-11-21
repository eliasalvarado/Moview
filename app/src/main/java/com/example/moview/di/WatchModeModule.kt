package com.example.moview.di

import android.content.Context
import androidx.room.Room
import com.example.moview.data.Repository.Estreno.EstrenoRepository
import com.example.moview.data.Repository.Estreno.EstrenoRepositoryImpl
import com.example.moview.data.local.dao.TituloDao
import com.example.moview.data.local.entity.WatchModeDb
import com.example.moview.data.remote.WatchModeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WatchModeModule {

    fun provideWatchModeDb(
        @ApplicationContext context: Context
    ): WatchModeDb {
        return Room.databaseBuilder(
            context,
            WatchModeDb::class.java,
            "watchModeDb"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTituloDao(
        database: WatchModeDb
    ): TituloDao {
        return database.tituloDao()
    }

    @Provides
    @Singleton
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRickAndMortyApi(
        client: OkHttpClient
    ) : WatchModeApi {
        return Retrofit.Builder()
            .baseUrl("https://api.watchmode.com/v1")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(WatchModeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        dao: TituloDao,
        api: WatchModeApi
    ): EstrenoRepository {
        return EstrenoRepositoryImpl(
            estrenoDao = dao,
            api = api
        )
    }
}