package com.felix.placebook.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

/**
 * Declare as an object, so it behaves like a singleton.
 */
object ImageUtils {

    /**
     * Save the Bitmap to permanent storage.
     */
    fun saveBitmapToFile(context: Context, bitmap: Bitmap, filename: String) {
        // Hole the image data.
        val stream = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

        val bytes = stream.toByteArray()

        ImageUtils.saveBytesToFile(context, bytes, filename)
    }

    /**
     * Save the bytes to a file
     */
    private fun saveBytesToFile(context: Context, bytes: ByteArray, filename: String) {
        val outputStream: FileOutputStream

        try {
            // Open a FileOutputStream using the given filename.
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE)
            // Bytes are written to the outputStream and then the stream closed.
            outputStream.write(bytes)
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadBitmapFromFile(context: Context, filename: String): Bitmap? {
        val filePath = File(context.filesDir, filename).absolutePath
        return BitmapFactory.decodeFile(filePath)
    }
}