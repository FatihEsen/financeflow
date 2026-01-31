package com.financeflow.ai.di

import android.content.Context
import androidx.room.Room
import com.financeflow.ai.data.local.AppDatabase
import com.financeflow.ai.data.local.TransactionDao
import com.financeflow.ai.data.repository.AiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "finance_flow.db"
        ).build()
    }

    @Provides
    fun provideTransactionDao(db: AppDatabase): TransactionDao {
        return db.transactionDao()
    }

    @Provides
    @Singleton
    fun provideAiRepository(@ApplicationContext context: Context): AiRepository {
        return AiRepository(context)
    }
}
