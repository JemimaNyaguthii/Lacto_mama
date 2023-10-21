package com.ajolla.lactomama.api

import com.ajolla.lactomama.model.Article
import com.ajolla.lactomama.model.ArticleRequest
import com.ajolla.lactomama.model.ArticleResponse
import com.ajolla.lactomama.model.CredentialRequest
import com.ajolla.lactomama.model.CredentialResponse
import com.ajolla.lactomama.model.Lactationist
import com.ajolla.lactomama.model.LactationistLoginRequest
import com.ajolla.lactomama.model.LactationistLoginResponse
import com.ajolla.lactomama.model.LactationistRequest
import com.ajolla.lactomama.model.LactationistResponse
import com.ajolla.lactomama.model.LoginRequest
import com.ajolla.lactomama.model.LoginResponse
import com.ajolla.lactomama.model.UploadCoursesRequest
import com.ajolla.lactomama.model.UploadCoursesResponse
import com.ajolla.lactomama.model.UserRequest
import com.ajolla.lactomama.model.UserResponse
import com.ajolla.lactomama.model.appointmentdata
import com.ajolla.lactomama.mother.cart.Course
import com.ajolla.lactomama.ui.EducationalMaterialData
import retrofit2.Call
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

    @GET("/api/articles/")
    suspend fun getArticles():Response<List<EducationalMaterialData>>
    @POST("/api/courses/")
    suspend fun postCourses(@Body coursesRequest:UploadCoursesRequest):Response<UploadCoursesResponse>
    @GET("/api/carts/")
    suspend fun getCart():Response<List<Course>>

    @POST("/api/articles/")
    suspend fun postArticles(@Body articleRequest: ArticleRequest): Response<ArticleResponse>

    @POST("/api/lactationists/")
    suspend fun postLactationists(@Body lactationistRequest:LactationistRequest): Response<LactationistResponse>
    @POST("/api/lactationists/")
    suspend fun lactationistlogin(@Body lactationistloginRequest: LactationistLoginRequest): Response<LactationistLoginResponse>

    @GET("/api/lactationists/")
      suspend fun getLactationists():Response<List<Lactationist>>

}