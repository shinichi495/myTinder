package com.namph.mytinder.data.response.user

data class Result(
    val seed: String,
    val user: User,
    val version: String
)