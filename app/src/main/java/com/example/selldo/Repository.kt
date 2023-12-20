package com.example.selldo

import android.util.Log
import okhttp3.ResponseBody

class Repository(private val apiService: ApiService) {

    suspend fun loginrequest(loginRequest: LoginRequest) : LoginResponse{
        Log.d("inrepo",""+loginRequest)
        return apiService.postloginrequest(loginRequest)
    }
}