package com.androproj.expencetracker.ui.insights

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.androproj.expencetracker.data.local.AppDatabase
import com.androproj.expencetracker.data.model.Transaction
import com.androproj.expencetracker.repository.FinanceRepository
import kotlinx.coroutines.Dispatchers

class InsightsViewModel(application: Application) : AndroidViewModel(application) {

    private val transactionDao = AppDatabase.getDatabase(application).transactionDao()
    private val goalDao = AppDatabase.getDatabase(application).goalDao()
    private val repository = FinanceRepository(transactionDao, goalDao)

    val allTransactions: LiveData<List<Transaction>> = repository.allTransactions
    val totalIncome: LiveData<Double> = repository.totalIncome
    val totalExpense: LiveData<Double> = repository.totalExpense

    fun getTransactionsByDateRange(startDate: Long, endDate: Long): LiveData<List<Transaction>> {
        return repository.getTransactionsByDateRange(startDate, endDate)
    }

    fun getWeeklyData(weekStartDate: Long): LiveData<List<Transaction>> {
        return repository.getTransactionsByDateRange(weekStartDate, System.currentTimeMillis())
    }
}

