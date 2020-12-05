package com.namph.mytinder.data.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName(value = "user")
    val user: User
)

data class User(
    @SerializedName(value = "SSN")
    val ssn: String,

    @SerializedName(value = "gender")
    val gender: String,

    @SerializedName(value = "name")
    val name: Name,

    @SerializedName(value = "location")
    val location: Location,

    @SerializedName(value = "email")
    val email: String,

    @SerializedName(value = "username")
    val username: String,

    @SerializedName(value = "phone")
    val phone: String,

    @SerializedName(value = "cell")
    val cell: String,

    @SerializedName(value = "picture")
    val picture: String,
    )

data class Name(
    @SerializedName("title")
    val title: String,

    @SerializedName("first")
    val first: String,

    @SerializedName("last")
    val last: String,


    )

data class Location(
    @SerializedName("street")
    val street: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("zip")
    val zip: String,
)