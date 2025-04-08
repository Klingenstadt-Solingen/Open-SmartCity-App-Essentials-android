package de.osca.android.essentials.domain.entity.elastic_search

import com.google.gson.annotations.SerializedName

data class ElasticSearchCountResponse(
    @SerializedName("count")
    val count: Int? = null,
)
