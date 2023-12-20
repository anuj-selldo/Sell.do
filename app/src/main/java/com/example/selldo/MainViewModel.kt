package com.example.selldo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class MainViewModel() : ViewModel() {

    private var _loginResposneLiveData = MutableLiveData<ResponseStatus<LoginResponse>>()
    val loginResponseLiveData : LiveData<ResponseStatus<LoginResponse>> get() = _loginResposneLiveData

    private var apiClient : ApiClient
    private var repository : Repository

    init{
        apiClient = ApiClient()
        repository = Repository(apiClient.getApiService())
    }

    fun loginRequest(loginrequest : LoginRequest){
        viewModelScope.launch {
            try{
                val loginResponseBody = repository.loginrequest(loginrequest)
                _loginResposneLiveData.postValue(ResponseStatus.Success(loginResponseBody))
                Log.d("LoginResposne",""+loginResponseBody)
            }catch (e: Exception){
                Log.d("Exception1234",""+e.message + "" + e.localizedMessage)
                _loginResposneLiveData.postValue(ResponseStatus.Error(e.message.toString()))
            }
        }
    }


}