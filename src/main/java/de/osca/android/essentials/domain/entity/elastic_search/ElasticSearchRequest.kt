package de.osca.android.essentials.domain.entity.elastic_search

import com.google.gson.annotations.SerializedName

data class ElasticSearchRequest(
    @SerializedName("index")
    val index: String,
    @SerializedName("query")
    val query: String,
    @SerializedName("raw")
    val raw: Boolean = true,
    @SerializedName("size")
    val size: Int? = null,
    @SerializedName("from")
    val from: Int = 0,
    @SerializedName("startDateIso")
    val startDate: String? = null,
    @SerializedName("endDateIso")
    val endDate: String? = null,
    @SerializedName("objectIds")
    val objectIds: List<String>? = null,
)
