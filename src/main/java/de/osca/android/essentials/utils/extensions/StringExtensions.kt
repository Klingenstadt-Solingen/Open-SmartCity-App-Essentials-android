package de.osca.android.essentials.utils.extensions

import android.util.Log
import de.osca.android.essentials.domain.entity.BooleanLiteral
import de.osca.android.essentials.utils.constants.DOMAIN_SUFFIX_DE
import de.osca.android.essentials.utils.constants.EMAIL_PATTERN
import de.osca.android.essentials.utils.constants.HEXADECIMAL_PATTERN
import java.util.regex.Pattern

data class LinkInfos(val url: String, val start: Int, val end: Int)

val urlPattern: Pattern = Pattern.compile(
    "\\b\\w*$DOMAIN_SUFFIX_DE.+?(?=\\.)|((?i)\\b((?:[a-z][\\w-]+:(?:\\/{1,3}|[a-z0-9%])|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}\\/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?«»“”‘’])))",
    Pattern.CASE_INSENSITIVE or Pattern.MULTILINE or Pattern.DOTALL
)

fun String?.extractUrls(): List<LinkInfos> {
    if (this == null) return emptyList()
    try {
        val matcher = urlPattern.matcher(this)
        var matchStart: Int
        var matchEnd: Int
        val links = arrayListOf<LinkInfos>()

        while (matcher.find()) {
            matchStart = matcher.start(1)
            matchEnd = matcher.end()

            var url = this.substring(matchStart, matchEnd)
            if (!url.startsWith("http://") && !url.startsWith("https://"))
                url = "https://$url"

            links.add(LinkInfos(url, matchStart, matchEnd))
        }
        return links
    } catch (e: Exception) {
        return emptyList()
    }
}

fun String?.isWWW(): Boolean {
    return this?.startsWith("www.") ?: false
}

fun String?.isEmail(): Boolean {
    val emailPattern: Pattern = Pattern.compile(
        EMAIL_PATTERN,
        Pattern.CASE_INSENSITIVE or Pattern.MULTILINE or Pattern.DOTALL
    )
    return this?.run {
        this.isNotEmpty() && emailPattern.matcher(this).matches()
    } ?: false
}

fun String?.isHexadecimal(): Boolean {
    val hexadecimalPattern = Pattern.compile(HEXADECIMAL_PATTERN)
    return hexadecimalPattern.matcher(this ?: "").matches()
}

fun String?.representsBoolean(): Boolean {
    return BooleanLiteral.fromString(this) != null
}

fun String?.toBoolean(): Boolean? {
    return BooleanLiteral.fromString(this)?.toBoolean()
}

fun String.toIntOrNull(): Int? {
    return try {
        this.toInt()
    } catch (exception: Exception) {
        null
    }
}