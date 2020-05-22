package com.mustafayigit.meetat.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mustafayigit.meetat.R
import com.mustafayigit.meetat.data.remote.ApiClient
import com.mustafayigit.meetat.data.remote.Status
import com.mustafayigit.meetat.data.remote.service.MeetingService
import com.mustafayigit.meetat.data.repository.MeetingRepository
import com.mustafayigit.meetat.viewmodel.MeetingViewModel
import com.mustafayigit.meetat.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var service: MeetingService
    private lateinit var repository: MeetingRepository
    private lateinit var viewModel: MeetingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service = ApiClient.getClient<MeetingService>().create(MeetingService::class.java)
        repository = MeetingRepository(service)
        viewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(repository)
            )[MeetingViewModel::class.java].apply { getMeetings() }

        initUI()
    }

    private fun initUI() {
        viewModel.meetingList.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.v("MAIN", "State: Loading - Data: ${it.data} - Message: ${it.message}")
                }
                Status.ERROR -> {
                    Log.v("MAIN", "State: Error - Data: ${it.data} - Message: ${it.message}")
                }
                Status.SUCCESS -> {
                    Log.v("MAIN", "State: Success - Data: ${it.data} - Message: ${it.message}")
                }
            }
        })
    }
}
