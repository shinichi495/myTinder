package com.namph.mytinder.data.db

import androidx.lifecycle.LiveData
import androidx.room.Delete

import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Insert
import androidx.room.Update
import com.namph.mytinder.data.db.user.UserData

interface BaseDao<T>  {
    fun selectAllPage () : List<UserData.User>

    @Insert(onConflict = IGNORE)
    fun insert(t : T) : Long

    @Insert(onConflict = REPLACE)
    fun insert (ts : List<T>)

    @Update(onConflict = IGNORE)
    fun update(t : T)

    @Update(onConflict = REPLACE)
    fun update (ts : List<T>)

//    @Delete
//    fun delete (t : T) : LiveData<T>
//
//    @Delete
//    fun delete (t : List<T>)

}