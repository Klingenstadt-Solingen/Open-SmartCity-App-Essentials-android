package de.osca.android.essentials.data.client

import co.infinum.retromock.Retromock
import retrofit2.Retrofit

sealed class OSCAHttpClient {
    class Default(val retrofit: Retrofit) : OSCAHttpClient()
    class Mocked(val retroMock: Retromock) : OSCAHttpClient()

    fun <T> create(service: Class<T>): T {
        return when(val client = this){
            is Default -> client.retrofit.create(service)
            is Mocked -> client.retroMock.create(service)
        }
    }
}