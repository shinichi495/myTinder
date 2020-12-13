package com.namph.mytinder.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.namph.mytinder.data.db.user.UserDao
import com.namph.mytinder.data.db.user.UserData

@Database(entities = [UserData.User::class], version = 1,exportSchema = false)
abstract class MyTiderDb : RoomDatabase () {
    abstract fun userDao() : UserDao
}