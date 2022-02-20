package com.example.weatherapp.container

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

object ImageBitmapString {
    @TypeConverter
    fun getStringFromBitmap(bitmapPitcture: Bitmap): ByteArray {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmapPitcture.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }

    @TypeConverter
    fun getBitmapFromStr(arr: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(arr, 0, arr.size)
    }
}