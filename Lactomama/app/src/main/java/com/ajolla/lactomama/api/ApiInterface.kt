package com.ajolla.lactomama.api

import com.ajolla.lactomama.model.CredentialRequest
import com.ajolla.lactomama.model.CredentialResponse
import com.ajolla.lactomama.model.LoginRequest
import com.ajolla.lactomama.model.LoginResponse
import com.ajolla.lactomama.model.UserRequest
import com.ajolla.lactomama.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/api/register/")
    suspend fun registerUser(@Body registerRequest: UserRequest): Response<UserResponse>

    @POST("api/login/")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("api/lisenses/")
    suspend fun CredentialUser(@Body credentialRequest: CredentialRequest): Response<CredentialResponse>


}