package com.namph.mytinder.data.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class UserData {

    @Entity(tableName = "user_table")
    data class User(@ColumnInfo(name = "ssn") @PrimaryKey(autoGenerate = false) val ssn : String,
                    @ColumnInfo(name = "titleName") val titleName : String,
                    @ColumnInfo(name = "firstName") val firstName : String,
                    @ColumnInfo(name = "lastName") val lastName : String,
                    @ColumnInfo(name = "street") val street : String,
                    @ColumnInfo(name = "city") val city : String,
                    @ColumnInfo(name = "state") val state : String,
                    @ColumnInfo(name = "phone") val phone : String,
                    @ColumnInfo(name = "picture") val picture : String,
                    @ColumnInfo(name = "zip") val zip : String,
                    @ColumnInfo(name = "email") val email : String,
                    @ColumnInfo(name = "username") val username : String,
                    @ColumnInfo(name = "cell") val cell : String
    )
}