package com.ajolla.lactomama.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajolla.lactomama.Repository.AppointmentRepository
//import com.ajolla.lactomama.Repository.CartRepository
import com.ajolla.lactomama.Repository.CoursesRepository
import com.ajolla.lactomama.Repository.CredentialRepository
import com.ajolla.lactomama.Repository.EducationalMaterialsRepository
import com.ajolla.lactomama.Repository.LoginRepository
import com.ajolla.lactomama.Repository.UserRepository
import com.ajolla.lactomama.api.ApiClient
import com.ajolla.lactomama.api.ApiInterface
import com.ajolla.lactomama.api.NewClient
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
import com.ajolla.lactomama.model.Product
import com.ajolla.lactomama.model.UploadCoursesRequest
import com.ajolla.lactomama.model.UserRequest
import com.ajolla.lactomama.model.UserResponse
import com.ajolla.lactomama.model.appointmentdata
import com.ajolla.lactomama.mother.cart.Course
import com.ajolla.lactomama.ui.EducationalMaterialData
import com.ajolla.lactomama.ui.home.ArticleData
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Call



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


class EducationalMaterialsViewModel : ViewModel() {
    val eduRepo = EducationalMaterialsRepository()
    var eduLiveData = MutableLiveData<List<ArticleData>>() // Use ArticleData
    var errorLiveData = MutableLiveData<String>()

    fun fetchArticles() {
        Log.d("EducationalMaterialsViewModel", "Fetching articles") // Log the fetching process
        viewModelScope.launch {
            val response = eduRepo.getArticles()
            if (response.isSuccessful) {
                val educationalMaterials = response.body() ?: emptyList()
                // Transform EducationalMaterialData into ArticleData
                val articles = educationalMaterials.map {
                    ArticleData(
                        it.id,
                        it.title,
                        it.description,
                        it.createdAt,
                        it.updatedAt,
                        it.content,
                        it.image
                    )
                }
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
//    fun getProducts(){
//        viewModelScope.launch {
//            val response =courseRepo.getCourses()
//            if (response.isSuccessful){
//                val courses=response.body()?: emptyList()
//                coursesLiveData.postValue(courses)
//            }
//            else{
//                errorLiveData.postValue(response.errorBody()?.string())
//            }
//        }
//    }

}
class CartViewModel : ViewModel() {
    private val apiInterface = NewClient.getRetrofitClient().create(ApiInterface::class.java)
    val cartLiveData = MutableLiveData<List<Product>>()
    val errorLiveData = MutableLiveData<String>()

    fun fetchCart() {
        viewModelScope.launch {
            try {
                val response = apiInterface.getProducts().execute()
                if (response.isSuccessful) {
                    val products = response.body() ?: ArrayList()  // or emptyList() if you prefer
                    cartLiveData.postValue(products)
                } else {
                    errorLiveData.postValue(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}


class ArticlesViewModel : ViewModel() {
    var errorLiveData = MutableLiveData<String>()
    private val apiInterface = ApiClient.buildClient(ApiInterface::class.java)

    private val articlesLiveData = MutableLiveData<List<ArticleResponse>>(emptyList())

    fun getArticles(): LiveData<List<ArticleResponse>> {
        return articlesLiveData
    }

    fun addArticle(article: ArticleResponse) {
        val currentList = articlesLiveData.value ?: emptyList()
        val updatedList = currentList.toMutableList()
        updatedList.add(article)
        articlesLiveData.postValue(updatedList)
    }

    suspend fun postArticles(articleRequest: ArticleRequest): Boolean {
        return try {
            val response = apiInterface.postArticles(articleRequest)

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


class LactationistViewModel : ViewModel() {
    private val apiInterface = ApiClient.buildClient(ApiInterface::class.java)

    val successLiveData = MutableLiveData<LactationistResponse>()
    val lactationistLiveData = MutableLiveData<List<Lactationist>>()
    val errorLiveData = MutableLiveData<String>()

    fun registerLactationist(lactationistRequest: LactationistRequest) {
        viewModelScope.launch {
            try {
                val response = apiInterface.postLactationists(lactationistRequest)
                if (response.isSuccessful) {
                    successLiveData.postValue(response.body())
                } else {
                    errorLiveData.postValue(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }

    fun fetchLactationists() {
        viewModelScope.launch {
            try {
                val response = apiInterface.getLactationists()
                if (response.isSuccessful) {
                    val lactationLists = response.body() ?: emptyList()
                    lactationistLiveData.postValue(lactationLists)
                } else {
                    errorLiveData.postValue(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}


class LactationistLoginViewModel : ViewModel() {
    private val apiInterface = ApiClient.buildClient(ApiInterface::class.java)

    val errorLiveData = MutableLiveData<String>()
    val lactLogLiveData = MutableLiveData<LactationistLoginResponse>()

    fun loginLactationist(lactationistLoginRequest: LactationistLoginRequest) {
        viewModelScope.launch {
            try {
                val response = apiInterface.lactationistlogin(lactationistLoginRequest)
                if (response.isSuccessful) {
                    lactLogLiveData.postValue(response.body())
                } else {
                    errorLiveData.postValue(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            }
        }
    }
}

//    class CartViewmodel:ViewModel(){
//        val cartRepo = CartRepository()
//        var cartLiveData = MutableLiveData<List<Course>>()
//        var errorLiveData = MutableLiveData<String>()
//        fun fetchCart() {
//            viewModelScope.launch {
//                val response = cartRepo.getCart()
//                if (response.isSuccessful) {
//                    val courses=response.body()?: emptyList()
//                    cartLiveData.postValue(courses)
//                } else {
//                    errorLiveData.postValue(response.errorBody()?.string())
//                }
//            }
//        }
//    }


