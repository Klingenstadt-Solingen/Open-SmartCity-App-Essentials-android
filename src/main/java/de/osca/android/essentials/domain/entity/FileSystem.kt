package de.osca.android.essentials.domain.entity

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import androidx.core.content.FileProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import de.osca.android.essentials.utils.extensions.toDateTimeString
import java.io.File
import java.io.FileWriter
import java.io.Reader
import java.io.Writer
import java.nio.file.Files
import java.time.LocalDateTime

//TODO: Replace by Realm before RC version
object FileSystem {

    fun writeSaveFileObject(context: Context, saveFileObject: SaveFileObject) {
        val file = File(context.filesDir.path + "/saveFileObject.json")

        if(file.exists()) {
            file.createNewFile()
        }

        val gson = GsonBuilder().setPrettyPrinting().create()

        val writer: Writer = FileWriter(file)
        gson.toJson(saveFileObject, writer)
        writer.flush()
        writer.close()
    }

    fun readSaveFileObject(context: Context): SaveFileObject {
        val file = File(context.filesDir.path + "/saveFileObject.json")

        return if(file.exists()) {
            val gson = Gson()
            val reader: Reader = Files.newBufferedReader(file.toPath())

            gson.fromJson(reader, SaveFileObject::class.java) ?: SaveFileObject()
        } else {
            SaveFileObject()
        }
    }

    fun getTempFileForImage(context: Context): File {
        val timeStamp = LocalDateTime.now().toDateTimeString()
        return File(context.filesDir.path + "/defect_${timeStamp}.jpg")
    }

    // rotates the bitmap
    fun rotateBitmap(bitmap: Bitmap, degrees: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degrees)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    // flips the bitmap
    fun flipBitmap(bitmap: Bitmap, horizontal: Boolean, vertical: Boolean): Bitmap {
        val matrix = Matrix()
        matrix.preScale(if (horizontal) -1f else 1f, if (vertical) -1f else 1f)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    fun loadImageFromFile(imageFile: File): Bitmap? {
        return try {
            val bitmap = BitmapFactory.decodeFile(imageFile.path)
            val exif = ExifInterface(imageFile.path)
            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
            // corrects the bitmap if the file is rotated or flipped
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270f)
                ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> flipBitmap(bitmap, true, false)
                ExifInterface.ORIENTATION_FLIP_VERTICAL -> flipBitmap(bitmap, false, true)
                else -> bitmap
            }
        } catch (e: Throwable) {
            null
        }
    }

    fun getImageUri(provider: String, context: Context, imageFile: File): Uri {
        return FileProvider.getUriForFile(
            context,
            provider,
            imageFile
        )
    }

    fun removeFile(fileToRemove: File): Boolean {
        return fileToRemove.delete()
    }
}

data class SaveFileObject(
    var dashboardConfig: List<String> = emptyList()
)
