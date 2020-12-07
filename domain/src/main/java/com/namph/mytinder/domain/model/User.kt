package com.namph.mytinder.domain.model

data class User(
    val ssn : String,
    val gender: String,
    val titleName : String,
    val firstName : String,
    val lastName : String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String,
    val email: String,
    val username: String,
    val phone : String,
    val cell : String,
    val picture : String
) {
    fun getName () : String {
        return "$firstName $lastName"
    }

    fun getAddress () : String {
        return "$street $city $state"
    }
}

data class Name(val title: String, val first: String, val last: String)
data class Location(val street: String, val city: String, val state: String, val zip: String)