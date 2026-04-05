package com.androproj.expencetracker.ui.dashboard

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.androproj.expencetracker.R
import com.androproj.expencetracker.ui.transactions.TransactionViewModel
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class DashboardActivity : AppCompatActivity() {

    private lateinit var viewModel: TransactionViewModel
    private var pieChart: PieChart? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val txtBalance = findViewById<TextView>(R.id.txtBalance)
        val txtIncome = findViewById<TextView>(R.id.txtIncome)
        val txtExpense = findViewById<TextView>(R.id.txtExpense)
        val txtSavings = findViewById<TextView>(R.id.txtSavings)
        pieChart = findViewById(R.id.pieChart)

        viewModel = ViewModelProvider(this)[TransactionViewModel::class.java]

        viewModel.allTransactions.observe(this) { list ->
            val income = list.filter { it.type == "income" }.sumOf { it.amount }
            val expense = list.filter { it.type == "expense" }.sumOf { it.amount }
            val balance = income - expense

            txtIncome.text = "Income: ₹${String.format("%.2f", income)}"
            txtExpense.text = "Expense: ₹${String.format("%.2f", expense)}"
            txtBalance.text = "Balance: ₹${String.format("%.2f", balance)}"
            txtSavings.text = "Savings: ${if (balance > 0) "✓" else "✗"} ₹${String.format("%.2f", balance)}"

            // Update pie chart
            updatePieChart(income, expense)
        }
    }

    private fun updatePieChart(income: Double, expense: Double) {
        pieChart?.let { chart ->
            val entries = mutableListOf<PieEntry>()
            if (income > 0) entries.add(PieEntry(income.toFloat(), "Income"))
            if (expense > 0) entries.add(PieEntry(expense.toFloat(), "Expense"))

            if (entries.isNotEmpty()) {
                val dataSet = PieDataSet(entries, "Financial Overview")
                dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
                dataSet.valueTextSize = 12f

                val pieData = PieData(dataSet)
                chart.data = pieData
                chart.description.text = ""
                chart.animateY(1000)
                chart.invalidate()
            }
        }
    }
}