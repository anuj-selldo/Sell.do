package com.example.selldo

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/mobile/create.json")
    suspend fun postloginrequest(@Body loginRequest: LoginRequest) : LoginResponse

}


