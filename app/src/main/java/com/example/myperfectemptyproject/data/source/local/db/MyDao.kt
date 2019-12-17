package com.example.myperfectemptyproject.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.myperfectemptyproject.base.BaseDao
import com.example.myperfectemptyproject.data.source.local.model.LocalModel

@Dao
interface MyDao : BaseDao<LocalModel> {

    @Query("SELECT * FROM LocalModel")
    fun getAll(): LiveData<List<LocalModel>>

}