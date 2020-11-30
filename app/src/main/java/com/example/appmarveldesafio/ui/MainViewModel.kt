package com.example.appmarveldesafio.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmarveldesafio.entities.Quadrinho
import com.example.appmarveldesafio.entities.Res
import com.example.appmarveldesafio.service.Repository
import com.example.appmarveldesafio.service.repository
import kotlinx.coroutines.launch

class MainViewModel(repository: Repository) : ViewModel() {

    val listResults = MutableLiveData<Res>()


    fun popListQuadrinhos() {
        try {
            viewModelScope.launch {
                listResults.value = repository.getComicsRepo(
                    0,
                    20,
                    "1",
                    "6eb7e8896ec5850c52515a8a23ee97f0",
                    "40a3aa568bb269dfad85ae0c4a297181"
                )
            }
        }catch (e : Exception){
            Log.i("MainViewModel", e.toString())
        }

    }

}