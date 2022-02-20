package com.example.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyImage(
    @field:ColumnInfo(name = "title") var title: String,
    @field:ColumnInfo(name = "photo") var photo: String,
    @field:ColumnInfo(
        name = "image",
        typeAffinity = ColumnInfo.BLOB
    ) var image: ByteArray
) {
    @PrimaryKey(autoGenerate = true)
    var uid = 0

}