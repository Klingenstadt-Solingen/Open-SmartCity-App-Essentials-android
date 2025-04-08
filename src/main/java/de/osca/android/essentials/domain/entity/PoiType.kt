package de.osca.android.essentials.domain.entity

enum class PoiType {
    WLAN,
    SHOPPING,
    SPORTS,
    CONSTRUCTION,
    LEISURE,
    PARKING,
    E_FUEL,
    PLAYGROUND,
    CARAVAN,
    CITIZEN_OFFICE;

    companion object {
        fun getFromName(name: String): PoiType? {
            return values().firstOrNull { it.name == name }
        }
    }
}