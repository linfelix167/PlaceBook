package com.felix.placebook.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

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

    private fun calculateInSampleSize(width: Int, height: Int, reqWidth: Int, reqHeight: Int): Int {
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

    fun loadBitmapFromFile(context: Context, filename: String): Bitmap? {
        val filePath = File(context.filesDir, filename).absolutePath
        return BitmapFactory.decodeFile(filePath)
    }

    @Throws(IOException::class)
    fun createUniqueImageFile(context: Context): File {
        val timeStamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val filename = "PlaceBook_" + timeStamp + "_"
        val filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(filename, ".jpg", filesDir)
    }

    fun decodeFileToSize(filePath: String, width: Int, height: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(filePath, options)

        options.inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight, width, height)

        options.inJustDecodeBounds = false

        return BitmapFactory.decodeFile(filePath, options)
    }

    fun decodeUriStreamToSize(uri: Uri, width: Int, height: Int, context: Context): Bitmap? {
        var inputStream: InputStream? = null
        try {
            val options: BitmapFactory.Options

            inputStream = context.contentResolver.openInputStream(uri)

            if (inputStream != null) {

                options = BitmapFactory.Options()
                options.inJustDecodeBounds = false
                BitmapFactory.decodeStream(inputStream, null, options)

                inputStream.close()
                inputStream = context.contentResolver.openInputStream(uri)
                if (inputStream != null) {

                    options.inSampleSize = calculateInSampleSize(options.outWidth, options.outHeight, width, height)
                    options.inJustDecodeBounds = false
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    inputStream.close()
                    return bitmap
                }
            }
            return null
        } catch (e: Exception) {
            return null
        } finally {
            inputStream?.close()
        }
    }
}