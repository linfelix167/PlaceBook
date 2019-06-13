package com.felix.placebook.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.content.Context
import android.graphics.Bitmap
import com.felix.placebook.util.ImageUtils

@Entity
data class Bookmark(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var placeId: String? = null,
    var name: String = "",
    var address: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    var phone: String = "",
    var notes: String = "",
    var category: String = ""
) {
    /**
     * Saving an image for a Bookmark
     */
    fun setImage(image: Bitmap, context: Context) {
        id?.let {
            ImageUtils.saveBitmapToFile(context, image, generateImageFilename(it))
        }
    }


    companion object {
        fun generateImageFilename(id: Long): String {
            return "bookmark$id.png"
        }
    }
}