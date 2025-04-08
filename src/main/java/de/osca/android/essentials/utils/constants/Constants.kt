package de.osca.android.essentials.utils.constants

import java.time.ZoneId

val DEFAULT_ZONE_ID = ZoneId.of("Europe/Berlin")

const val DATE_FORMAT = "dd.MM.yyyy"
const val TIME_FORMAT = "HH:mm"
const val DATE_TIME_FORMAT = "$DATE_FORMAT $TIME_FORMAT"
const val DECIMAL_FORMAT = "###,##0.0"
const val PARAM_CONTENT_TYPE = "Content-Type"
const val CONTENT_TYPE_TEXT = "text/plain"
const val CONTENT_TYPE_JSON = "application/json"
const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
const val ISO_8601_NO_MILLISECONDS = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val API_DATE_FORMAT = ISO_8601
const val API_SUCCESS_STATUS_CODE = 200
const val CONTENT_TYPE_HTML = "text/HTML"
const val UNICODE_UTF8 = "UTF-8"
const val EMAIL_PATTERN = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
const val INTENT_TYPE_CALENDAR_EVENT = "vnd.android.cursor.item/event"
const val FACEBOOK_URL = "https://www.facebook.com"
const val DOMAIN_SUFFIX_DE = ".de"
const val TYPE_DRAWABLE = "drawable"
const val HEXADECIMAL_PATTERN = "-?[0-9a-fA-F]+"
const val DEFAULT_API_LIST_RESPONSE_LIMIT = 1000

const val ONE_DAY_IN_MILLI = 86_400_000L