package com.example.retrofitget


data class Data(
	val code: Int,
	val data: ArrayList<DataX?>? = ArrayList(),
	val message: String,
	val status: String
)
data class DataX(
	val created_at: String,
	val description: String,
	val icon: String,
	val id: Int,
	val link: String,
	val name: String
)