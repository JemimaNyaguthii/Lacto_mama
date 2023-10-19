package com.ajolla.lactomama.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajolla.lactomama.Repository.AppointmentRepository
import com.ajolla.lactomama.Repository.ArticleRepository
import com.ajolla.lactomama.Repository.CartRepository
import com.ajolla.lactomama.Repository.CoursesRepository
import com.ajolla.lactomama.Repository.CredentialRepository
import com.ajolla.lactomama.Repository.EducationalMaterialsRepository
import com.ajolla.lactomama.Repository.LoginRepository
import com.ajolla.lactomama.Repository.UserRepository
import com.ajolla.lactomama.model.ArticleRequest
import com.ajolla.lactomama.model.ArticleResponse
import com.ajolla.lactomama.model.CredentialRequest
import com.ajolla.lactomama.model.CredentialResponse
import com.ajolla.lactomama.model.LoginRequest
import com.ajolla.lactomama.model.LoginResponse
import com.ajolla.lactomama.model.UploadCoursesRequest
import com.ajolla.lactomama.model.UserRequest
import com.ajolla.lactomama.model.UserResponse
import com.ajolla.lactomama.model.appointmentdata
import com.ajolla.lactomama.mother.CoursesData
import com.ajolla.lactomama.mother.cart.Course
import com.ajolla.lactomama.ui.EducationalMaterialData
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


class EducationalMaterialsViewModel:ViewModel(){
    val eduRepo = EducationalMaterialsRepository()
    var eduLiveData = MutableLiveData<List<EducationalMaterialData>>()
    var errorLiveData = MutableLiveData<String>()
    fun fetchArticles() {
        viewModelScope.launch {
            val response = eduRepo.getArticles()
            if (response.isSuccessful) {
                val articles=response.body()?: emptyList()

                eduLiveData.postValue(articles)
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}
class CoursesViewModel : ViewModel() {
    private val courseRepo = CoursesRepository()
    var coursesLiveData= MutableLiveData<List<Course>>()
    var errorLiveData = MutableLiveData<String>()

    suspend fun uploadCourse(uploadCoursesRequest: UploadCoursesRequest): Boolean {
        return try {
            val response = courseRepo.postCourses(uploadCoursesRequest)
            response.isSuccessful
        } catch (e: Exception) {
            errorLiveData.postValue(e.message)
            false
        }
    }
    fun fetchCourses(){
        viewModelScope.launch {
            val response =courseRepo.getCourses()
            if (response.isSuccessful){
                val courses=response.body()?: emptyList()
                coursesLiveData.postValue(courses)
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

}
class CartViewmodel:ViewModel(){
    val cartRepo = CartRepository()
    var cartLiveData = MutableLiveData<List<Course>>()
    var errorLiveData = MutableLiveData<String>()
    fun fetchCart() {
        viewModelScope.launch {
            val response = cartRepo.getCart()
            if (response.isSuccessful) {
                val courses=response.body()?: emptyList()
                cartLiveData.postValue(courses)
            } else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}
//class ArticlesViewModel : ViewModel() {
//    private val articleRepo = ArticleRepository()
//    var errorLiveData = MutableLiveData<String>()
//
//    suspend fun postArticles(articleRequest: ArticleRequest): Boolean {
//        return try {
//            val response = articleRepo.postArticles(articleRequest)
//            response.isSuccessful
//        } catch (e: Exception) {
//            errorLiveData.postValue(e.message)
//            false
//        }
//    }
//}


class ArticlesViewModel : ViewModel() {
    private val articleRepo = ArticleRepository()
    var errorLiveData = MutableLiveData<String>()


    private val articlesLiveData = MutableLiveData<List<ArticleResponse>>()

    fun getArticles(): LiveData<List<ArticleResponse>> {
        return articlesLiveData
    }

    fun addArticle(article: ArticleResponse) {
        val currentList = articlesLiveData.value ?: emptyList()
        val updatedList = currentList.toMutableList()
        updatedList.add(article)
        articlesLiveData.value = updatedList
    }

    suspend fun postArticles(articleRequest: ArticleRequest): Boolean {
        return try {
            val response = articleRepo.postArticles(articleRequest)
            val success = response.isSuccessful

            if (success) {
                val postedArticle = response.body()
                if (postedArticle != null) {

                    addArticle(postedArticle)
                } else {

                    errorLiveData.postValue("Response body is null")
                }
            }

            success
        } catch (e: Exception) {
            errorLiveData.postValue(e.message)
            false
        }
    }

}

