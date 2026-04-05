package com.androproj.expencetracker.ui.goals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.androproj.expencetracker.data.local.AppDatabase
import com.androproj.expencetracker.data.model.Goal
import com.androproj.expencetracker.repository.FinanceRepository
import kotlinx.coroutines.launch

class GoalViewModel(application: Application) : AndroidViewModel(application) {

    private val transactionDao = AppDatabase.getDatabase(application).transactionDao()
    private val goalDao = AppDatabase.getDatabase(application).goalDao()
    private val repository = FinanceRepository(transactionDao, goalDao)

    val allGoals: LiveData<List<Goal>> = repository.allGoals

    fun insertGoal(goal: Goal) = viewModelScope.launch {
        repository.insertGoal(goal)
    }

    fun updateGoal(goal: Goal) = viewModelScope.launch {
        repository.updateGoal(goal)
    }

    fun deleteGoal(goal: Goal) = viewModelScope.launch {
        repository.deleteGoal(goal)
    }

    fun getGoalById(goalId: Int) = viewModelScope.launch {
        repository.getGoalById(goalId)
    }
}

