package com.example.weatherapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.Dao.UserDao
import com.example.weatherapp.container.ImageBitmapString
import com.example.weatherapp.model.User

@Database(entities = [User::class],version = 6, exportSchema = false)

abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        var instance:UserDatabase?=null
        private const val DATABASE_NAME="User"

        fun getInstance(context: Context):UserDatabase?
        {
            if(instance == null)
            {
                synchronized(UserDatabase::class.java)
                {
                    if(instance == null)
                    {
                        instance= Room.databaseBuilder(context,UserDatabase::class.java,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return instance
        }

    }
}
