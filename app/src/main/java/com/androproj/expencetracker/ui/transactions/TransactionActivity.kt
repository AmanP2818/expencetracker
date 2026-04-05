package com.androproj.expencetracker.ui.transactions

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androproj.expencetracker.R
import com.androproj.expencetracker.data.model.Transaction
import com.androproj.expencetracker.utils.DateUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class TransactionActivity : AppCompatActivity() {

    private lateinit var viewModel: TransactionViewModel
    private lateinit var adapter: TransactionAdapter
    private var selectedDate = System.currentTimeMillis()
    private var editingTransaction: Transaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fab = findViewById<FloatingActionButton>(R.id.fabAdd)

        adapter = TransactionAdapter(
            onEditClick = { transaction -> showEditDialog(transaction) },
            onDeleteClick = { transaction -> deleteTransaction(transaction) }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this)[TransactionViewModel::class.java]

        viewModel.allTransactions.observe(this) {
            adapter.submitList(it)
        }

        fab.setOnClickListener {
            editingTransaction = null
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_transaction, null)

        val etAmount = dialogView.findViewById<EditText>(R.id.etAmount)
        val etCategory = dialogView.findViewById<EditText>(R.id.etCategory)
        val etNote = dialogView.findViewById<EditText>(R.id.etNote)
        val spinnerType = dialogView.findViewById<Spinner>(R.id.spinnerType)
        val btnPickDate = dialogView.findViewById<Button>(R.id.btnPickDate)
        val txtSelectedDate = dialogView.findViewById<TextView>(R.id.txtSelectedDate)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)

        // Setup type spinner
        val typeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listOf("Income", "Expense"))
        spinnerType.adapter = typeAdapter

        selectedDate = System.currentTimeMillis()
        txtSelectedDate.text = DateUtils.formatDate(selectedDate)

        // Populate if editing
        editingTransaction?.let {
            etAmount.setText(it.amount.toString())
            etCategory.setText(it.category)
            etNote.setText(it.note)
            spinnerType.setSelection(if (it.type == "income") 0 else 1)
            selectedDate = it.date
            txtSelectedDate.text = DateUtils.formatDate(selectedDate)
        }

        btnPickDate.setOnClickListener {
            showDatePicker { date ->
                selectedDate = date
                txtSelectedDate.text = DateUtils.formatDate(selectedDate)
            }
        }

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        btnSave.setOnClickListener {
            val amount = etAmount.text.toString().toDoubleOrNull()
            val category = etCategory.text.toString()
            val type = if (spinnerType.selectedItemPosition == 0) "income" else "expense"
            val note = etNote.text.toString()

            if (amount == null || category.isEmpty()) {
                Toast.makeText(this, "Please enter amount and category", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val transaction = if (editingTransaction != null) {
                editingTransaction!!.copy(
                    amount = amount,
                    type = type,
                    category = category,
                    date = selectedDate,
                    note = note
                )
            } else {
                Transaction(
                    amount = amount,
                    type = type,
                    category = category,
                    date = selectedDate,
                    note = note
                )
            }

            if (editingTransaction != null) {
                viewModel.update(transaction)
                Toast.makeText(this, "Transaction updated", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insert(transaction)
                Toast.makeText(this, "Transaction added", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showEditDialog(transaction: Transaction) {
        editingTransaction = transaction
        showAddDialog()
    }

    private fun deleteTransaction(transaction: Transaction) {
        AlertDialog.Builder(this)
            .setTitle("Delete Transaction")
            .setMessage("Are you sure you want to delete this transaction?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.delete(transaction)
                Toast.makeText(this, "Transaction deleted", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun showDatePicker(onDateSelected: (Long) -> Unit) {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selectedDate

        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, dayOfMonth)
                onDateSelected(selectedCalendar.timeInMillis)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
}