package com.androproj.expencetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.androproj.expencetracker.data.local.AppDatabase
import com.androproj.expencetracker.ui.dashboard.DashboardActivity
import com.androproj.expencetracker.ui.goals.GoalActivity
import com.androproj.expencetracker.ui.insights.InsightsActivity
import com.androproj.expencetracker.ui.transactions.TransactionActivity
import com.androproj.expencetracker.utils.DataInitializer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize sample data
        val database = AppDatabase.getDatabase(this)
        DataInitializer.initializeSampleData(database)

        findViewById<Button>(R.id.btnDashboard).setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }

        findViewById<Button>(R.id.btnTransactions).setOnClickListener {
            startActivity(Intent(this, TransactionActivity::class.java))
        }

        findViewById<Button>(R.id.btnGoals).setOnClickListener {
            startActivity(Intent(this, GoalActivity::class.java))
        }

        findViewById<Button>(R.id.btnInsights).setOnClickListener {
            startActivity(Intent(this, InsightsActivity::class.java))
        }

    }
}