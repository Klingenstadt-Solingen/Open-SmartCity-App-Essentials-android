package de.osca.android.essentials.domain.entity.server

import com.google.gson.annotations.SerializedName

data class ServerParams(
    @SerializedName("privacyVersion")
    val privacyVersion: Int? = null,
    @SerializedName("defectDataEmailContent")
    val defectDataEmailContent: String? = null,
    @SerializedName("defectDataDefaultEmailSubject")
    val defectDataDefaultEmailSubject: String? = null,
    @SerializedName("defaultEmailFromAddress")
    val defaultEmailFromAddress: String? = null,

    @SerializedName("imprintText")
    val imprintText: String? = null,
    @SerializedName("privacyText")
    val privacyText: String? = null,
    @SerializedName("accessibilityText")
    val accessibilityText: String? = null,
    @SerializedName("sponsorText")
    val sponsorText: String? = null,
    @SerializedName("openSourceLicenses")
    val openSourceLicenses: String? = null,
    @SerializedName("gdprVersion")
    val gdprVersion: Int? = null,
    @SerializedName("termsVersion")
    val termsVersion: Int? = null,
    @SerializedName("termsText")
    val termsText: String? = null,
    @SerializedName("headerImageUrl")
    val headerImageUrl: String? = null,

    @SerializedName("legalType")
    val legalType: String? = null,
    @SerializedName("imprintURL")
    val imprintUrl: String? = null,
    @SerializedName("privacyURL")
    val privacyUrl: String? = null,
    @SerializedName("accessibilityURL")
    val accessibilityUrl: String? = null,

    @SerializedName("coronaTitleImage")
    val coronaTitleImage: String? = null,
)
