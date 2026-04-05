package com.androproj.expencetracker.ui.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androproj.expencetracker.R
import com.androproj.expencetracker.data.model.Transaction
import com.androproj.expencetracker.utils.DateUtils

class TransactionAdapter(
    private val onEditClick: (Transaction) -> Unit = {},
    private val onDeleteClick: (Transaction) -> Unit = {}
) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private var list = listOf<Transaction>()

    fun submitList(newList: List<Transaction>) {
        list = newList
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val amount: TextView = view.findViewById(R.id.txtAmount)
        val category: TextView = view.findViewById(R.id.txtCategory)
        val date: TextView = view.findViewById(R.id.txtDate)
        val type: TextView = view.findViewById(R.id.txtType)
        val editBtn: ImageButton = view.findViewById(R.id.btnEdit)
        val deleteBtn: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.amount.text = "₹${String.format("%.2f", item.amount)}"
        holder.category.text = item.category
        holder.date.text = DateUtils.formatDate(item.date)
        holder.type.text = item.type.uppercase()
        
        val textColor = if (item.type == "income") {
            holder.amount.context.getColor(android.R.color.holo_green_dark)
        } else {
            holder.amount.context.getColor(android.R.color.holo_red_dark)
        }
        holder.amount.setTextColor(textColor)

        holder.editBtn.setOnClickListener {
            onEditClick(item)
        }

        holder.deleteBtn.setOnClickListener {
            onDeleteClick(item)
        }
    }
}