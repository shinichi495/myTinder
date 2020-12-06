package com.namph.mytinder.data.service

import com.namph.mytinder.data.response.user.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("/api/0.4/?randomapi")
    suspend fun getUser () : Response<UserResponse>
}