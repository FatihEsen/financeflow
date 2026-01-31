package com.financeflow.ai.data.local

import androidx.room.*
import com.financeflow.ai.domain.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactions(transactions: List<Transaction>)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Query("SELECT COALESCE(SUM(amount), 0.0) FROM transactions WHERE date >= :startDate")
    fun getTotalSpendingSince(startDate: Long): Flow<Double>

    @Query("SELECT category, COALESCE(SUM(amount), 0.0) as total FROM transactions GROUP BY category")
    fun getSpendingByCategory(): Flow<List<CategorySpending>>
}

data class CategorySpending(
    val category: String,
    val total: Double
)
