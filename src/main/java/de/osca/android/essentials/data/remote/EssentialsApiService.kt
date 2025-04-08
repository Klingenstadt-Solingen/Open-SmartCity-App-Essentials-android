package de.osca.android.essentials.data.remote

import de.osca.android.essentials.domain.entity.elastic_search.ElasticSearchRequest
import de.osca.android.essentials.domain.entity.elastic_search.ElasticSearchResponse
import de.osca.android.essentials.domain.entity.server.ServerConfig
import de.osca.android.essentials.utils.annotations.UnwrappedResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EssentialsApiService {

    @UnwrappedResponse
    @GET("config")
    suspend fun getConfig(): Response<ServerConfig>

    @POST("functions/elastic-search")
    suspend fun elasticSearch(
        @Body elasticSearchRequest: ElasticSearchRequest
    ): Response<List<ElasticSearchResponse>>
}