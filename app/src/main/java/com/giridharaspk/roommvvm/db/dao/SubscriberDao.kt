package com.giridharaspk.roommvvm.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.giridharaspk.roommvvm.db.Subscriber

@Dao
interface SubscriberDao {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber): Long //if expecting a response for confirmation

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)//: Int //returns num of rows

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscriber_data_table")
    fun getAllSubscribers(): LiveData<List<Subscriber>>
    // runs in background thread using coroutines by room db when returning LiveData

}