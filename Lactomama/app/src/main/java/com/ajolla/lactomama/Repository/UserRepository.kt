package com.ajolla.lactomama.Repository

import com.ajolla.lactomama.api.ApiClient
import com.ajolla.lactomama.api.ApiInterface
import com.ajolla.lactomama.model.CredentialRequest
import com.ajolla.lactomama.model.CredentialResponse
import com.ajolla.lactomama.model.LoginRequest
import com.ajolla.lactomama.model.LoginResponse
import com.ajolla.lactomama.model.UserRequest
import com.ajolla.lactomama.model.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)
    suspend fun registerUser(registerRequest: UserRequest): Response<UserResponse> {
        return withContext(Dispatchers.IO) {
            apiClient.registerUser(registerRequest)
        }
    }
}

class LoginRepository {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)
    suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO) {
            apiClient.loginUser(loginRequest)
        }
    }
}

class CredentialRepository {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)
    suspend fun credentialUser(credentialRequest: CredentialRequest): Response<CredentialResponse> {
        return withContext(Dispatchers.IO){
            apiClient.CredentialUser(credentialRequest)
        }
    }

}