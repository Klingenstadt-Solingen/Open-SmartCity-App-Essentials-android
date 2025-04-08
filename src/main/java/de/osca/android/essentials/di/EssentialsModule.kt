package de.osca.android.essentials.di

import android.content.Context
import co.infinum.retromock.Retromock
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.osca.android.essentials.BuildConfig
import de.osca.android.essentials.data.remote.EssentialsApiService
import de.osca.android.essentials.data.client.OSCAHttpClient
import de.osca.android.essentials.data.storage.EssentialsStorageImpl
import de.osca.android.essentials.domain.boundaries.EssentialsStorage
import de.osca.android.essentials.presentation.base.DataErrorHandler
import de.osca.android.essentials.utils.strings.EssentialsStrings
import de.osca.android.essentials.utils.strings.EssentialsStringsImpl
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EssentialsModule {
    @Singleton
    @Provides
    fun essentialsApiService(oscaHttpClient: OSCAHttpClient): EssentialsApiService =
        oscaHttpClient.create(EssentialsApiService::class.java)

    @Singleton
    @Provides
    fun provideStrings(@ApplicationContext context: Context): EssentialsStrings {
        return EssentialsStringsImpl(context)
    }

    @Singleton
    @Provides
    fun provideCustomHttpClient(retrofit: Retrofit): OSCAHttpClient {
        return OSCAHttpClient.Default(retrofit)
    }

    @Singleton
    @Provides
    fun provideDataErrorHandler(essentialsStrings: EssentialsStrings): DataErrorHandler {
        return DataErrorHandler(essentialsStrings)
    }

    @Singleton
    @Provides
    fun providesEssentialsStorage(storage: EssentialsStorageImpl): EssentialsStorage {
        return storage
    }
}