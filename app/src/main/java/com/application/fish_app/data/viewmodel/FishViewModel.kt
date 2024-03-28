package com.application.fish_app.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.fish_app.data.database.Fish
import com.application.fish_app.data.database.FishDao
import com.application.fish_app.data.database.FishDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FishViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: FishDao
    private val _allFishes = MutableLiveData<List<Fish>>()
    private val _getFish = MutableLiveData<Fish>()

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    val allFishes: LiveData<List<Fish>> = _allFishes
    val getFish: LiveData<Fish> = _getFish

    init {
        dao = FishDatabase.getInstance(application).fishDao()
    }

    fun getFishes() {
        viewModelScope.launch(Dispatchers.IO) {
            _allFishes.postValue(dao.getAllFishes())
        }
    }

    fun getFish(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _getFish.postValue(dao.getFish(id))
        }
    }

    fun addFish(fish: Fish) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {dao.insertFish(fish)}
        }
        getFishes()
    }

    fun deleteFish(fish: Fish) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {dao.deleteFish(fish)}
        }
        getFishes()
    }

    fun updateFish(id: Int, fishName: String, amount: String, fishColor: String, price: String) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {
                dao.updateFish(id, fishName, amount, fishColor, price)
            }
        }
        getFishes()
    }
}