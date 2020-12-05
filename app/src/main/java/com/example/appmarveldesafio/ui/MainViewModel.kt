package com.example.appmarveldesafio.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import com.example.appmarveldesafio.R
import com.example.appmarveldesafio.entities.Images
import com.example.appmarveldesafio.entities.Quadrinho
import com.example.appmarveldesafio.entities.Res
import com.example.appmarveldesafio.service.Repository
import com.example.appmarveldesafio.service.repository
import com.example.appmarveldesafio.ui.adapters.AdapterQuadrinhos
import kotlinx.coroutines.launch

class MainViewModel(repository: Repository) : ViewModel() {

    val listResults = MutableLiveData<Res>()
    val quadDetail = MutableLiveData<Quadrinho>()
    val listQuadrinho = MutableLiveData<ArrayList<Quadrinho>>()

    init {
        listQuadrinho.value = ArrayList()
    }


    fun popListQuadrinhos(offset: Int) {
        try {
            viewModelScope.launch {
                listResults.value = repository.getComicsRepo(
                    offset,
                    20,
                    "1",
                    "6eb7e8896ec5850c52515a8a23ee97f0",
                    "40a3aa568bb269dfad85ae0c4a297181"
                )
                listResults.value!!.data.results.forEach {
                    when(it.description){
                        null -> it.description = "NO DESCRIPTION"
                    }
                    it.thumbnail.path = it.thumbnail.path.replace("http", "https")

                    when(it.images.size){
                        0 -> {
                            it.images.add(Images(it.thumbnail.path, it.thumbnail.extension))
                        }
                        else -> {
                            it.images.forEach{
                                it.path = it.path.replace("http", "https")
                            }
                        }
                    }
                }
                listQuadrinho.value!!.addAll(listResults.value!!.data.results)
            }
        }catch (e : Exception){
            Log.i("MainViewModel", e.toString())
        }
    }

    fun updatePositionQuadDetail(position: Int){
        viewModelScope.launch {
            quadDetail.value = listQuadrinho.value!![position]
        }
    }




}

