package com.giridharaspk.roommvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.giridharaspk.roommvvm.databinding.ActivityMainBinding
import com.giridharaspk.roommvvm.db.SubscriberDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private val TAG: String = MainActivity::class.simpleName!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val dao = SubscriberDatabase.getInstance(application).subscriberDao
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)

        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        binding.subscriberViewModel = subscriberViewModel
        binding.lifecycleOwner = this

        displaySubscribersList()

    }

    private fun displaySubscribersList() {
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.i(TAG, it.toString())
        })
    }

}