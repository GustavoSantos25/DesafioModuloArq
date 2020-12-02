package com.example.appmarveldesafio.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class Res(val data: Data)

data class Data(val offset: Int, var results: ArrayList<Quadrinho>)

data class Quadrinho(val id: Int, var title: String, var issueNumber: Double, var description : String, var pageCount : Int,  var dates: ArrayList<Date>, var prices: ArrayList<Prices>, var thumbnail : Thumbnail, var images : ArrayList<Images>)

data class Images(var path: String, var extension: String)

data class Thumbnail(var path: String, var extension: String)

data class Prices(var type: String, var price: Double)

data class Date(var type: String, var date: String)