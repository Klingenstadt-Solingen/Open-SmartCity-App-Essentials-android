package de.osca.android.essentials.domain.entity.elastic_search

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.internal.LinkedTreeMap

data class ElasticSearchResponse(
    @SerializedName("_index")
    val index: String? = null,
    @SerializedName("_type")
    val type: String? = null,
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("_score")
    val score: Double? = null,
    @SerializedName("_source")
    val source: ElasticSearchSource? = null
) {
    inline fun <reified T> retrieveSourceAs(gson: Gson): T? {
        val sourceJson = gson.toJson(source)
        return gson.fromJson(sourceJson, T::class.java)
    }
}

data class ElasticSearchSource(
    @SerializedName("_streetAddress")
    val streetAddress: String? = null,
    @SerializedName("_houseNumber")
    val houseNumber: String? = null,
    @SerializedName("_suggest")
    val suggest: String? = null
) {}