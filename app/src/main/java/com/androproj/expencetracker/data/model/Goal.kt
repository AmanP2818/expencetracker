package com.androproj.expencetracker.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class Goal(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val targetAmount: Double,
    val currentAmount: Double = 0.0,
    val category: String = "",
    val deadline: Long = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val description: String = ""
)

