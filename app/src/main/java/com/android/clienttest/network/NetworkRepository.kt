package com.android.clienttest.network

class NetworkRepository constructor(private val retrofitService: RetrofitService) {
    suspend fun getAllUsers() = retrofitService.getUsers()
}