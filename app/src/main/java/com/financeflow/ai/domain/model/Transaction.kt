package com.financeflow.ai.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val date: Long,
    val merchant: String,
    val amount: Double,
    val category: String, // Grocery, Tech, Entertainment, Food, Other
    val description: String?,
    val isAIGenerated: Boolean = true
)
