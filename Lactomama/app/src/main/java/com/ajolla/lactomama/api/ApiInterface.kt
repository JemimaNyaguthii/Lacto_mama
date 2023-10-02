package com.ajolla.lactomama.api

import com.ajolla.lactomama.model.AppointmentResponse
import com.ajolla.lactomama.model.Article
import com.ajolla.lactomama.model.CredentialRequest
import com.ajolla.lactomama.model.CredentialResponse
import com.ajolla.lactomama.model.LoginRequest
import com.ajolla.lactomama.model.LoginResponse
import com.ajolla.lactomama.model.UserRequest
import com.ajolla.lactomama.model.UserResponse
import com.ajolla.lactomama.model.appointmentdata
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @POST("/api/register/")
    suspend fun registerUser(@Body registerRequest: UserRequest): Response<UserResponse>

    @POST("api/login/")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("api/lisenses/")
    suspend fun CredentialUser(@Body credentialRequest: CredentialRequest): Response<CredentialResponse>

    @GET("/api/appointments/")
    suspend fun getAppointments(): Response<List<appointmentdata>>

    @GET("/api/appointments/{id}")
    suspend fun getArticlebyId(@Path("id") productId: Int): Response<Article>








}