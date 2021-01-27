package com.giridharaspk.roommvvm

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giridharaspk.roommvvm.db.Subscriber
import kotlinx.coroutines.launch

class SubscriberViewModel(private val repo: SubscriberRepository) : ViewModel(), Observable {

    val subscribers = repo.subscribers

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButton = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButton = MutableLiveData<String>()

    init {
        saveOrUpdateButton.value = "Save"
        clearAllOrDeleteButton.value = "Clear All"
    }

    fun saveOrUpdate() {
        val name = inputName.value!!
        val email = inputEmail.value!!
        insert(Subscriber(0, name, email)) //0 is ignored and autoincremented
        inputEmail.value = null
        inputName.value = null
    }

    fun clearAllOrDelete() {
        clearAll()
    }


    fun insert(subscriber: Subscriber) =
        viewModelScope.launch {
            repo.insert(subscriber)
        }

    fun delete(subscriber: Subscriber) = viewModelScope.launch {
        repo.delete(subscriber)
    }

    fun update(subscriber: Subscriber) = viewModelScope.launch {
        repo.update(subscriber)
    }

    fun clearAll() = viewModelScope.launch { repo.deleteAllSubscribers() }
    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}