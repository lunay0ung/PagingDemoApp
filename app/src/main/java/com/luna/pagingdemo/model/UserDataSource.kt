package com.luna.pagingdemo.model

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.luna.pagingdemo.api.ApiService
import com.luna.pagingdemo.api.ApiServiceBuilder
import com.luna.pagingdemo.api.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource : PageKeyedDataSource<Int, User>() {
    private val TAG = UserDataSource::class.java.simpleName

    companion object {
        const val PAGE_SIZE = 6
        const val FIRST_PAGE = 1
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getUsers(params.key)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    Log.d(TAG, ">>>>> loadBefore key가 뭐지? ${params.key}")
                    Log.d(TAG, ">>>>> loadBefore requestedLoadSize가 뭐지? ${params.requestedLoadSize}")

                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, User>) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getUsers(FIRST_PAGE)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users

                    Log.d(TAG, ">>>>> loadInitial FIRST_PAGE 뭐지? ${FIRST_PAGE}")
                    Log.d(TAG, ">>>>> loadInitial requestedLoadSize가 뭐지? ${params.requestedLoadSize}")

                    responseItems?.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.getUsers(params.key)
        call.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.users
                    val key = params.key + 1

                    Log.d(TAG, ">>>>> loadAfter key 뭐지? ${key}")
                    Log.d(TAG, ">>>>> loadAfter requestedLoadSize가 뭐지? ${params.requestedLoadSize}")
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }



}