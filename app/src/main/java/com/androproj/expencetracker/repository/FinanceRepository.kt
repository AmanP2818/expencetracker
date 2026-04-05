package com.androproj.expencetracker.repository

import com.androproj.expencetracker.data.local.GoalDao
import com.androproj.expencetracker.data.local.TransactionDao
import com.androproj.expencetracker.data.model.Goal
import com.androproj.expencetracker.data.model.Transaction

class FinanceRepository(
    private val transactionDao: TransactionDao,
    private val goalDao: GoalDao
) {

    val allTransactions = transactionDao.getAllTransactions()
    val allGoals = goalDao.getAllGoals()
    val totalIncome = transactionDao.getTotalIncome()
    val totalExpense = transactionDao.getTotalExpense()

    // Transaction operations
    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    suspend fun update(transaction: Transaction) {
        transactionDao.update(transaction)
    }

    suspend fun delete(transaction: Transaction) {
        transactionDao.delete(transaction)
    }

    fun getTransactionsByType(type: String) = transactionDao.getTransactionsByType(type)
    fun getTransactionsByCategory(category: String) = transactionDao.getTransactionsByCategory(category)
    fun getTransactionsByDateRange(startDate: Long, endDate: Long) = transactionDao.getTransactionsByDateRange(startDate, endDate)

    suspend fun getExpenseCategories() = transactionDao.getExpenseCategories()
    suspend fun getIncomeCategories() = transactionDao.getIncomeCategories()

    // Goal operations
    suspend fun insertGoal(goal: Goal) {
        goalDao.insert(goal)
    }

    suspend fun updateGoal(goal: Goal) {
        goalDao.update(goal)
    }

    suspend fun deleteGoal(goal: Goal) {
        goalDao.delete(goal)
    }

    suspend fun getGoalById(goalId: Int) = goalDao.getGoalById(goalId)
}