package com.ajolla.lactomama.Repository

import com.ajolla.lactomama.api.ApiClient
import com.ajolla.lactomama.api.ApiInterface
import com.ajolla.lactomama.model.AppointmentResponse
import com.ajolla.lactomama.model.ArticleRequest
import com.ajolla.lactomama.model.ArticleResponse
import com.ajolla.lactomama.model.CredentialRequest
import com.ajolla.lactomama.model.CredentialResponse
import com.ajolla.lactomama.model.LoginRequest
import com.ajolla.lactomama.model.LoginResponse
import com.ajolla.lactomama.model.UploadCoursesRequest
import com.ajolla.lactomama.model.UploadCoursesResponse
import com.ajolla.lactomama.model.UserRequest
import com.ajolla.lactomama.model.UserResponse
import com.ajolla.lactomama.model.appointmentdata
import com.ajolla.lactomama.mother.CoursesData
import com.ajolla.lactomama.mother.cart.Course
import com.ajolla.lactomama.ui.EducationalMaterialData
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

class AppointmentRepository {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)

    suspend fun getAppointment():Response<List<appointmentdata>>{
        return withContext(Dispatchers.IO){
            apiClient.getAppointments()
        }
    }
}

class EducationalMaterialsRepository {
    private val apiClient= ApiClient.buildClient(ApiInterface::class.java)
    suspend fun getArticles(): Response<List<EducationalMaterialData>> {
        return withContext(Dispatchers.IO){
            apiClient.getArticles()
        }
    }
}

class CoursesRepository {
    val apiClient= ApiClient.buildClient(ApiInterface::class.java)
    suspend fun postCourses(coursesRequest: UploadCoursesRequest):Response<UploadCoursesResponse>{
        return withContext(Dispatchers.IO){
            apiClient.postCourses(coursesRequest)
        }
    }
}
class CartRepository {
    val apiClient = ApiClient.buildClient(ApiInterface::class.java)
    suspend fun getCart():Response<List<Course>>{
        return withContext(Dispatchers.IO){
            apiClient.getCart()
        }
    }
}
class ArticleRepository {
    val apiClient= ApiClient.buildClient(ApiInterface::class.java)
    suspend fun postArticles(articleRequest: ArticleRequest):Response<ArticleResponse>{
        return withContext(Dispatchers.IO){
            apiClient.postArticles(articleRequest)
        }
    }
}