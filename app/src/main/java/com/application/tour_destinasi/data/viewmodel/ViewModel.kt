package com.application.tour_destinasi.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.destination_app.data.database.AppDao
import com.application.destination_app.data.database.Destination
import com.application.destination_app.data.database.DestinationDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: AppDao
    private val _allDestinations = MutableLiveData<List<Destination>>()
    private val _getDestination = MutableLiveData<Destination>()

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    val allDestinations: LiveData<List<Destination>> = _allDestinations
    val getDestination: LiveData<Destination> = _getDestination

    init {
        dao = DestinationDatabase.getInstance(application).AppDao()
    }

    fun getDestinations() {
        viewModelScope.launch(Dispatchers.IO) {
            _allDestinations.postValue(dao.getALLDestinations())
        }
    }

    fun getDestination(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _getDestination.postValue(dao.getDestination(id))
        }
    }

    fun addDestination(destination: Destination) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {dao.insertDestination(destination)}
        }
        getDestinations()
    }

    fun deleteDestination(destination: Destination) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {dao.deleteDestination(destination)}
        }
        getDestinations()
    }

    fun updateDestination(id: Int, destinationName: String, amount: String, dColor: String, price: String) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {
                dao.updateDestination(id, destinationName, amount, dColor, price)
            }
        }
        getDestinations()
    }
}