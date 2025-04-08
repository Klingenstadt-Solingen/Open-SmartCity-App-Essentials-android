package de.osca.android.essentials.utils.testing

import android.content.Context
import co.infinum.retromock.BodyFactory
import java.io.*

/**
 * This class allows building a Responde Body from plain json files
 * When a context is provided, gets the json file from 'res > raw' folder
 * When a context is not provided, gets the json file from 'resources' folder
 **/
class ResourceBodyFactory(
    private val context: Context? = null
) : BodyFactory {

    /**
     * @param input: full Json file name, including suffix
     */
    @Throws(IOException::class)
    override fun create(input: String): InputStream {
        return when (context) {
            null -> FileInputStream(File(ResourceBodyFactory::class.java.getResource("/$input").path))
            else -> getContextJsonResource(input) ?: throw Exception("Resource not found: $input")
        }
    }

    private fun getContextJsonResource(input: String): InputStream? {
        return context?.run {
            val resourceName = input.removeSuffix(JSON_FILE_SUFFIX)
            val resourceId = resources.getIdentifier(resourceName, TYPE_RAW, packageName)
            resources.openRawResource(resourceId)
        }
    }

    companion object {
        const val JSON_FILE_SUFFIX = ".json"
        const val TYPE_RAW = "raw"
    }
}

