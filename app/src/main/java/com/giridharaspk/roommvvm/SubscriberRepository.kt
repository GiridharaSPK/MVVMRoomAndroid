package com.giridharaspk.roommvvm

import com.giridharaspk.roommvvm.db.Subscriber
import com.giridharaspk.roommvvm.db.dao.SubscriberDao

class SubscriberRepository(private val dao: SubscriberDao) {
    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber) {
        dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber: Subscriber) {
        dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber) {
        dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAllSubscribers() {
        dao.deleteAll()
    }

}