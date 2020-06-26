package com.luna.pagingdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.luna.pagingdemo.bookModel.BookDataSource
import com.luna.pagingdemo.bookModel.BookDataSourceFactory
import com.luna.pagingdemo.model.User
import com.luna.pagingdemo.model.UserDataSource
import com.luna.pagingdemo.model.UserDataSourceFactory
import com.luna.searchbooks.model.Book

class BookViewModel: ViewModel() {
    var bookPagedList: LiveData<PagedList<Book>>
    private var liveDataSource: LiveData<BookDataSource>
    init {
        val itemDataSourceFactory = BookDataSourceFactory()
        liveDataSource = itemDataSourceFactory.bookLiveDataSource
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(BookDataSource.PAGE_SIZE)
            .build()
        bookPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()
    }

}