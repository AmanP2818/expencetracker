package com.androproj.expencetracker.ui.insights

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.androproj.expencetracker.R
import com.androproj.expencetracker.utils.DateUtils
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*

class InsightsActivity : AppCompatActivity() {

    private lateinit var insightsViewModel: InsightsViewModel
    private var barChart: BarChart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insights)

        val txtTopCategory = findViewById<TextView>(R.id.txtTopCategory)
        val txtTotalExpense = findViewById<TextView>(R.id.txtTotalExpense)
        val txtAvgTransaction = findViewById<TextView>(R.id.txtAvgTransaction)
        val txtWeeklyTrend = findViewById<TextView>(R.id.txtWeeklyTrend)
        barChart = findViewById(R.id.barChart)

        insightsViewModel = ViewModelProvider(this)[InsightsViewModel::class.java]

        insightsViewModel.allTransactions.observe(this) { transactions ->
            if (transactions.isEmpty()) {
                txtTopCategory.text = "No data yet"
                txtTotalExpense.text = "Total Expense: ₹0"
                txtAvgTransaction.text = "Avg Transaction: ₹0"
                txtWeeklyTrend.text = "Weekly Trend: No data"
                return@observe
            }

            // Top Category Analysis
            val expenses = transactions.filter { it.type == "expense" }
            val totalExpense = expenses.sumOf { it.amount }

            val categoryMap = mutableMapOf<String, Double>()
            for (item in expenses) {
                categoryMap[item.category] = categoryMap.getOrDefault(item.category, 0.0) + item.amount
            }

            val topCategory = categoryMap.maxByOrNull { it.value }?.key ?: "-"
            val topCategoryAmount = categoryMap.maxByOrNull { it.value }?.value ?: 0.0

            txtTopCategory.text = "Top Spending: $topCategory (₹${String.format("%.2f", topCategoryAmount)})"
            txtTotalExpense.text = "Total Expense: ₹${String.format("%.2f", totalExpense)}"

            // Average Transaction
            val avgTransaction = if (transactions.isNotEmpty()) {
                transactions.sumOf { it.amount } / transactions.size
            } else {
                0.0
            }
            txtAvgTransaction.text = "Avg Transaction: ₹${String.format("%.2f", avgTransaction)}"

            // Weekly Trend
            val weekStartDate = DateUtils.getStartOfWeek()
            val weekTransactions = transactions.filter { it.date >= weekStartDate }
            val weeklyExpense = weekTransactions.filter { it.type == "expense" }.sumOf { it.amount }
            val weeklyIncome = weekTransactions.filter { it.type == "income" }.sumOf { it.amount }

            txtWeeklyTrend.text = "This Week: Income ₹${String.format("%.2f", weeklyIncome)} | Expense ₹${String.format("%.2f", weeklyExpense)}"

            // Update Bar Chart
            updateBarChart(weeklyIncome.toFloat(), weeklyExpense.toFloat())
        }
    }

    private fun updateBarChart(income: Float, expense: Float) {
        barChart?.let { chart ->
            val entries = listOf(
                BarEntry(1f, income),
                BarEntry(2f, expense)
            )

            val dataSet = BarDataSet(entries, "Weekly Overview")
            dataSet.colors = listOf(
                android.graphics.Color.parseColor("#4CAF50"),
                android.graphics.Color.parseColor("#F44336")
            )
            dataSet.valueTextSize = 12f

            val barData = BarData(dataSet)
            chart.data = barData
            chart.description.text = ""
            chart.xAxis.setDrawLabels(false)
            chart.animateY(1000)
            chart.invalidate()
        }
    }
}