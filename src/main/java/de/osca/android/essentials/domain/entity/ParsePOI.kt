package de.osca.android.essentials.domain.entity

import com.google.android.gms.maps.model.LatLng
import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ktx.delegates.attribute
import com.parse.ktx.delegates.stringAttribute

@ParseClassName("POI")
open class ParsePOI : ParseObject() {
    val name: String by stringAttribute()

    val geopoint: LatLng? by latLngAttribute()

    /**
     * the sourceId of PoiCategory
     */
    val poiCategory: String by stringAttribute()

    /**
     * Might not be set, if Parse does not include "details" key in query.
     * Accessing the field will then result in a crash.
     *
     * To solve this problem, a derived data class could be used that implements this field while the superclass does not.
     */
    val details: List<Map<String, Any>> by attribute()
}
