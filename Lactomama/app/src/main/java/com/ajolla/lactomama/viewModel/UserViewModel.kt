package com.ajolla.lactomama.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajolla.lactomama.Repository.AppointmentRepository
import com.ajolla.lactomama.Repository.CredentialRepository
import com.ajolla.lactomama.Repository.LoginRepository
import com.ajolla.lactomama.Repository.UserRepository
import com.ajolla.lactomama.model.Article
import com.ajolla.lactomama.model.CredentialRequest
import com.ajolla.lactomama.model.CredentialResponse
import com.ajolla.lactomama.model.LoginRequest
import com.ajolla.lactomama.model.LoginResponse
import com.ajolla.lactomama.model.UserRequest
import com.ajolla.lactomama.model.UserResponse
import com.ajolla.lactomama.model.appointmentdata
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    var UserRepo = UserRepository()
    val successLiveData = MutableLiveData<UserResponse>()
    val errorLiveData = MutableLiveData<String>()
    fun registerUser(registerRequest: UserRequest) {
        viewModelScope.launch{
            val response = UserRepo.registerUser(registerRequest)
            if (response.isSuccessful) {
                successLiveData.postValue(response.body())
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

class LoginViewModel : ViewModel() {
    val loginRepository = LoginRepository()
    val errorLiveData = MutableLiveData<String>()
    val logLiveData = MutableLiveData<LoginResponse>()
    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val response = loginRepository.loginUser(loginRequest)
            if (response.isSuccessful) {
                logLiveData.postValue(response.body())
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

class CredentialViewModel : ViewModel() {
    val credentialRepository = CredentialRepository()
    val errorLiveData = MutableLiveData<String>()
    val credLiveData = MutableLiveData<CredentialResponse>()
    fun credentialUser(credentialRequest: CredentialRequest) {
        viewModelScope.launch {
            val response = credentialRepository.credentialUser(credentialRequest)
            if (response.isSuccessful) {
                credLiveData.postValue(response.body())
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}

class AppointmentViewModel:ViewModel (){

    val appointmentRepo =AppointmentRepository()
    val appointmentLiveData = MutableLiveData<List<appointmentdata>>()
    val errorLiveData = MutableLiveData<String>()
    fun fetchAppointments() {
        viewModelScope.launch {
            val response = appointmentRepo.getAppointment()
            if (response.isSuccessful) {
                val appointments=response.body()?: emptyList()
                appointmentLiveData.postValue(appointments)
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}


