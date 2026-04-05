package com.androproj.expencetracker.utils

import com.androproj.expencetracker.data.local.AppDatabase
import com.androproj.expencetracker.data.model.Transaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

object DataInitializer {

    fun initializeSampleData(database: AppDatabase) {
        CoroutineScope(Dispatchers.IO).launch {
            val transactionDao = database.transactionDao()

            // Check if data already exists
            val existingData = transactionDao.getAllTransactions().value
            if (!existingData.isNullOrEmpty()) {
                return@launch
            }

            val calendar = Calendar.getInstance()
            val sampleTransactions = mutableListOf<Transaction>()

            // Today's transactions
            sampleTransactions.add(
                Transaction(
                    amount = 2500.0,
                    type = "income",
                    category = "Salary",
                    date = System.currentTimeMillis(),
                    note = "Monthly salary"
                )
            )

            sampleTransactions.add(
                Transaction(
                    amount = 450.0,
                    type = "expense",
                    category = "Food",
                    date = System.currentTimeMillis(),
                    note = "Lunch and dinner"
                )
            )

            // Yesterday
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            sampleTransactions.add(
                Transaction(
                    amount = 1200.0,
                    type = "expense",
                    category = "Transport",
                    date = calendar.timeInMillis,
                    note = "Monthly metro pass"
                )
            )

            // 2 days ago
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            sampleTransactions.add(
                Transaction(
                    amount = 800.0,
                    type = "expense",
                    category = "Utilities",
                    date = calendar.timeInMillis,
                    note = "Electricity bill"
                )
            )

            // 3 days ago
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            sampleTransactions.add(
                Transaction(
                    amount = 350.0,
                    type = "expense",
                    category = "Entertainment",
                    date = calendar.timeInMillis,
                    note = "Movie tickets"
                )
            )

            // 4 days ago
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            sampleTransactions.add(
                Transaction(
                    amount = 1500.0,
                    type = "income",
                    category = "Freelance",
                    date = calendar.timeInMillis,
                    note = "Project payment"
                )
            )

            // 5 days ago
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            sampleTransactions.add(
                Transaction(
                    amount = 200.0,
                    type = "expense",
                    category = "Food",
                    date = calendar.timeInMillis,
                    note = "Groceries"
                )
            )

            // 6 days ago
            calendar.add(Calendar.DAY_OF_YEAR, -1)
            sampleTransactions.add(
                Transaction(
                    amount = 5000.0,
                    type = "expense",
                    category = "Shopping",
                    date = calendar.timeInMillis,
                    note = "New clothes"
                )
            )

            // Insert all sample transactions
            for (transaction in sampleTransactions) {
                transactionDao.insert(transaction)
            }
        }
    }
}

