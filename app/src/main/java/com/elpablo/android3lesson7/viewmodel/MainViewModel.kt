package com.elpablo.android3lesson7.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elpablo.android3lesson7.BuildConfig
import com.elpablo.android3lesson7.model.APODAPImpl
import com.elpablo.android3lesson7.model.APODDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel(
    private val apodImpl: APODAPImpl = APODAPImpl()
) : ViewModel() {

    var serverResponse by mutableStateOf<APODDTO?>(null)

    fun sendServerRequest(){
        val currentDate = Calendar.getInstance()
        val formatted = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        formatted.timeZone = TimeZone.getTimeZone("EST")
        val date = formatted.format(currentDate.time)
        apodImpl.getAPODImpl().getAPOD(BuildConfig.NASA_API_KEY, date).enqueue(
            object : Callback<APODDTO> {
                override fun onResponse(call: Call<APODDTO>, response: Response<APODDTO>) {
                    if (response.isSuccessful&&response.body()!=null){
                        response.body()?.let {
                            serverResponse = it

                        }
                    }
                }

                override fun onFailure(call: Call<APODDTO>, t: Throwable) {
                    throw Exception("Ошибка получения данных")
                }
            }
        )
    }
}