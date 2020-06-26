package com.luna.pagingdemo.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

/*
* Using Data Source we need DataSource.Factory which extends DataSource.Factory.
* Basically it just a factory for DataSources. So first create an instance of data source so that we can use it in our RecyclerView.
*
* */
class UserDataSourceFactory : DataSource.Factory<Int, User>() {

    val userLiveDataSource = MutableLiveData<UserDataSource>()
    override fun create(): DataSource<Int, User> {
        val userDataSource = UserDataSource()
        userLiveDataSource.postValue(userDataSource)
        return userDataSource
    }
}