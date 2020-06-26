package com.luna.pagingdemo.bookModel

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.luna.searchbooks.model.Book

/*
* Using Data Source we need DataSource.Factory which extends DataSource.Factory.
* Basically it just a factory for DataSources. So first create an instance of data source so that we can use it in our RecyclerView.
*
* */
class BookDataSourceFactory : DataSource.Factory<Int, Book>() {

    val bookLiveDataSource = MutableLiveData<BookDataSource>()
    override fun create(): DataSource<Int, Book> {
        val bookDataSource = BookDataSource()
        bookLiveDataSource.postValue(bookDataSource)
        return bookDataSource
    }
}