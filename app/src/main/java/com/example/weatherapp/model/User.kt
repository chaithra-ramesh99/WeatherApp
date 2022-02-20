package com.example.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")


class User(
    @field:ColumnInfo(name = "name") var name: String,
    @field:ColumnInfo(name = "lname") var lname: String,
    @field:ColumnInfo(name = "email") var email: String,
    @field:ColumnInfo(
        name = "imagePath"
    ) var image: ByteArray
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null



   /* data class User(@field:ColumnInfo(name = "name") var name: String,
                    @field:ColumnInfo(name = "lname") var lname: String,
                    @field:ColumnInfo(name = "email") var email: String,
                    @field:ColumnInfo(name = "imageList") var imageList: ArrayList<String>
    ) {

        @PrimaryKey(autoGenerate = true)
        var id:Int?=null




    }

*/


}
