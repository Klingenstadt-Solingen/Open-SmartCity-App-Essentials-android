package de.osca.android.essentials.domain.entity

sealed class BooleanLiteral(val representations: List<String>) {
    object True : BooleanLiteral(representations = listOf("yes", "1", "on"))
    object False : BooleanLiteral(representations = listOf("no", "0", "off"))

    fun toBoolean(): Boolean {
        return this == True
    }

    companion object {
        fun fromString(value: String?): BooleanLiteral? {
            val literals = BooleanLiteral::class.sealedSubclasses.mapNotNull { it.objectInstance }
            return literals.firstOrNull { it.representations.contains(value?.lowercase()) }
        }
    }
}