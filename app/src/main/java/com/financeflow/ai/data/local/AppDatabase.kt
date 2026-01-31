package com.financeflow.ai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.financeflow.ai.domain.model.Transaction

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
