package com.financeflow.ai.ui.dashboard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.financeflow.ai.domain.model.Transaction
import com.financeflow.ai.ui.theme.GlassWhite
import com.financeflow.ai.ui.theme.Indigo600
import com.financeflow.ai.ui.theme.Slate950
import com.financeflow.ai.ui.theme.Teal400
import java.text.SimpleDateFormat
import java.util.*
import com.financeflow.ai.R

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MonthlySummaryScreen(
    viewModel: DashboardViewModel,
    onBack: () -> Unit
) {
    val monthlySummary by viewModel.monthlyGroupedTransactions.collectAsState()

    Scaffold(
        containerColor = Slate950,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Monthly Overview", color = Color.White, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            monthlySummary.forEach { (monthYear, transactions) ->
                stickyHeader {
                    GlassCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    ) {
                        Column(Modifier.padding(16.dp)) {
                            Text(
                                text = monthYear,
                                style = MaterialTheme.typography.titleMedium,
                                color = Indigo600,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(8.dp))
                            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                val income = transactions.filter { !it.isAIGenerated && it.amount < 0 }.sumOf { kotlin.math.abs(it.amount) }
                                val expense = transactions.filter { it.amount > 0 && it.category != "Payment" }.sumOf { it.amount }
                                
                                Column {
                                    Text("Manual Salary", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                                    Text("₺${String.format("%.2f", income)}", color = Teal400, fontWeight = FontWeight.Bold)
                                }
                                Column(horizontalAlignment = Alignment.End) {
                                    Text("Spending", style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                                    Text("₺${String.format("%.2f", expense)}", color = Color.Red, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }

                items(transactions) { transaction ->
                    TransactionItem(transaction)
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
    }
}
