package com.luna.pagingdemo.bookModel

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.luna.pagingdemo.api.ApiService
import com.luna.pagingdemo.api.ApiServiceBuilder
import com.luna.pagingdemo.api.BookSearchResponse
import com.luna.pagingdemo.api.UserResponse
import com.luna.searchbooks.model.Book
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookDataSource : PageKeyedDataSource<Int, Book>() {
    private val TAG = BookDataSource::class.java.simpleName

    companion object {
        const val PAGE_SIZE = 50
        const val FIRST_PAGE = 1
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Book>) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.searchBooks(params.key, keyword = "말라리아")
        call.enqueue(object : Callback<BookSearchResponse> {
            override fun onResponse(call: Call<BookSearchResponse>, response: Response<BookSearchResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.books
                    Log.d(TAG, ">>>>> loadBefore key가 뭐지? ${params.key}")
                    Log.d(TAG, ">>>>> loadBefore requestedLoadSize가 뭐지? ${params.requestedLoadSize}")

                    val key = if (params.key > 1) params.key - 1 else 0
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }
            override fun onFailure(call: Call<BookSearchResponse>, t: Throwable) {
                Log.d(TAG, "")
            }
        })
    }
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Book>) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.searchBooks(FIRST_PAGE, "말라리아")
        call.enqueue(object : Callback<BookSearchResponse> {
            override fun onResponse(call: Call<BookSearchResponse>, response: Response<BookSearchResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.books

                    Log.d(TAG, ">>>>> loadInitial FIRST_PAGE 뭐지? ${FIRST_PAGE}")
                    Log.d(TAG, ">>>>> loadInitial requestedLoadSize가 뭐지? ${params.requestedLoadSize}")

                    responseItems?.let {
                        callback.onResult(responseItems, null, FIRST_PAGE + 1)
                    }
                }
            }
            override fun onFailure(call: Call<BookSearchResponse>, t: Throwable) {
            }
        })
    }
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Book>) {
        val service = ApiServiceBuilder.buildService(ApiService::class.java)
        val call = service.searchBooks(params.key, keyword = "말라리아")
        call.enqueue(object : Callback<BookSearchResponse> {
            override fun onResponse(call: Call<BookSearchResponse>, response: Response<BookSearchResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.books
                    val key = params.key + 1

                    Log.d(TAG, ">>>>> loadAfter key 뭐지? ${key}")
                    Log.d(TAG, ">>>>> loadAfter requestedLoadSize가 뭐지? ${params.requestedLoadSize}")
                    responseItems?.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }
            override fun onFailure(call: Call<BookSearchResponse>, t: Throwable) {
            }
        })
    }



}