package com.namph.mytinder.data.db.user

import androidx.room.Dao
import androidx.room.Query
import com.namph.mytinder.data.db.BaseDao

@Dao
interface UserDao : BaseDao<UserData.User>  {

    @Query("SELECT * FROM user_table")
    override fun selectAllPage(): List<UserData.User>
}