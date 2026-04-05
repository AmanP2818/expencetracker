package com.androproj.expencetracker.ui.goals

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androproj.expencetracker.R
import com.androproj.expencetracker.data.model.Goal
import com.androproj.expencetracker.ui.transactions.TransactionViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GoalActivity : AppCompatActivity() {

    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var goalViewModel: GoalViewModel
    private var currentBalance = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        val etGoal = findViewById<EditText>(R.id.etGoal)
        val btnSetGoal = findViewById<Button>(R.id.btnSetGoal)
        val txtProgress = findViewById<TextView>(R.id.txtProgress)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val fab = findViewById<FloatingActionButton>(R.id.fabAddGoal)

        transactionViewModel = ViewModelProvider(this)[TransactionViewModel::class.java]
        goalViewModel = ViewModelProvider(this)[GoalViewModel::class.java]

        // Calculate balance from transactions
        transactionViewModel.allTransactions.observe(this) { list ->
            val income = list.filter { it.type == "income" }.sumOf { it.amount }
            val expense = list.filter { it.type == "expense" }.sumOf { it.amount }
            currentBalance = income - expense

            updateProgressDisplay(currentBalance, txtProgress, progressBar)
        }

        btnSetGoal.setOnClickListener {
            val goalAmount = etGoal.text.toString().toDoubleOrNull()
            if (goalAmount == null || goalAmount <= 0) {
                Toast.makeText(this, "Enter a valid goal amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val goal = Goal(
                title = "Monthly Savings Goal",
                targetAmount = goalAmount,
                currentAmount = currentBalance,
                category = "Savings"
            )
            goalViewModel.insertGoal(goal)
            Toast.makeText(this, "Goal set successfully", Toast.LENGTH_SHORT).show()
            etGoal.text.clear()
        }

        fab.setOnClickListener {
            showAddGoalDialog()
        }

        goalViewModel.allGoals.observe(this) { goals ->
            // Display goals if needed
            if (goals.isNotEmpty()) {
                val latestGoal = goals.first()
                etGoal.setText(latestGoal.targetAmount.toString())
            }
        }
    }

    private fun updateProgressDisplay(
        saved: Double,
        txtProgress: TextView,
        progressBar: ProgressBar
    ) {
        txtProgress.text = "Current Balance: ₹${String.format("%.2f", saved)}"
    }

    private fun showAddGoalDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_goal, null)

        val etTitle = dialogView.findViewById<EditText>(R.id.etGoalTitle)
        val etAmount = dialogView.findViewById<EditText>(R.id.etGoalAmount)
        val etCategory = dialogView.findViewById<EditText>(R.id.etGoalCategory)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSaveGoal)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        btnSave.setOnClickListener {
            val title = etTitle.text.toString()
            val amount = etAmount.text.toString().toDoubleOrNull()
            val category = etCategory.text.toString()

            if (title.isEmpty() || amount == null || amount <= 0) {
                Toast.makeText(this, "Enter valid goal details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val goal = Goal(
                title = title,
                targetAmount = amount,
                currentAmount = currentBalance,
                category = category,
                deadline = System.currentTimeMillis() + (30 * 24 * 60 * 60 * 1000) // 30 days
            )

            goalViewModel.insertGoal(goal)
            Toast.makeText(this, "Goal created: $title", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }
}