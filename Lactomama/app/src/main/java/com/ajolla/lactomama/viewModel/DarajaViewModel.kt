package com.example.amalimobile.ui.home.donation
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajolla.lactomama.Repository.DarajaRepository
import com.ajolla.lactomama.model.DarajaRequest
import com.ajolla.lactomama.model.DarajaResponse
import kotlinx.coroutines.launch
class DarajaViewModel :ViewModel() {
    val paymentRepo= DarajaRepository()
    val payLiveData=MutableLiveData<DarajaResponse>()
    val errLiveData=MutableLiveData<String>()
    private val _paymentStatus = MutableLiveData<PaymentStatus>()
    val paymentStatus: LiveData<PaymentStatus>
        get() = _paymentStatus
    init {
        _paymentStatus.value = PaymentStatus.NONE
    }
    fun paymentSuccessful() {
        _paymentStatus.value = PaymentStatus.SUCCESSFUL
    }
    fun paymentFailed() {
        _paymentStatus.value = PaymentStatus.FAILED
    }
    enum class PaymentStatus {
        NONE,
        SUCCESSFUL,
        FAILED
    }
    fun payment(paymentRequest: DarajaRequest) {
        viewModelScope.launch {
            try {
                val response = paymentRepo.initiateSTKPush(paymentRequest)
                if (response.isSuccessful) {
                    paymentSuccessful()
                } else {
                    paymentFailed()
                    errLiveData.postValue(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                paymentFailed()
                errLiveData.postValue(e.message)
            }
        }
    }
}