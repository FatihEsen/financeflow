package com.financeflow.ai.ui.dashboard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import com.financeflow.ai.ui.components.PDFPicker
import com.financeflow.ai.ui.components.SwipeableTransactionItem
import com.financeflow.ai.domain.model.Transaction
import androidx.compose.ui.res.stringResource
import com.financeflow.ai.R
import kotlinx.coroutines.launch
import com.financeflow.ai.ui.theme.GlassWhite
import com.financeflow.ai.ui.theme.Indigo400
import com.financeflow.ai.ui.theme.Indigo500
import com.financeflow.ai.ui.theme.Indigo600
import com.financeflow.ai.ui.theme.Slate900
import com.financeflow.ai.ui.theme.Slate950
import com.financeflow.ai.ui.theme.Teal400

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {
    val transactions by viewModel.transactions.collectAsState()
    val totalSpending by viewModel.totalSpending.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val apiKey by viewModel.apiKey.collectAsState()
    val haptic = androidx.compose.ui.platform.LocalHapticFeedback.current
    val snackbarHostState = remember { SnackbarHostState() }
    val context = androidx.compose.ui.platform.LocalContext.current

    var showApiKeyDialog by remember { mutableStateOf(false) }
    var showManualEntryDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiState) {
        when (uiState) {
            is DashboardState.Success -> {
                haptic.performHapticFeedback(androidx.compose.ui.hapticfeedback.HapticFeedbackType.LongPress)
                val message = context.getString((uiState as DashboardState.Success).messageResId)
                snackbarHostState.showSnackbar(message)
            }
            is DashboardState.Error -> {
                val errorState = uiState as DashboardState.Error
                val error = context.getString(errorState.errorResId, errorState.dynamicValue ?: "")
                snackbarHostState.showSnackbar(error)
            }
            is DashboardState.MissingApiKey -> {
                showApiKeyDialog = true
            }
            else -> {}
        }
    }

    if (showApiKeyDialog) {
        com.financeflow.ai.ui.components.ApiKeyDialog(
            currentKey = apiKey ?: "",
            currentProvider = viewModel.provider.collectAsState().value,
            currentBaseUrl = viewModel.baseUrl.collectAsState().value,
            currentModel = viewModel.modelName.collectAsState().value,
            onDismiss = { showApiKeyDialog = false },
            onSave = { k, p, u, m -> viewModel.saveSettings(k, p, u, m) }
        )
    }

    if (showManualEntryDialog) {
        com.financeflow.ai.ui.components.ManualEntryDialog(
            onDismiss = { showManualEntryDialog = false },
            onConfirm = { m, a, c, inc -> viewModel.addManualTransaction(m, a, c, inc) },
            predictCategory = { viewModel.predictCategory(it) },
            isPredicting = viewModel.isPredictingCategory.collectAsState().value
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            Column(horizontalAlignment = Alignment.End) {
                SmallFloatingActionButton(
                    onClick = { showManualEntryDialog = true },
                    containerColor = Indigo600,
                    contentColor = Color.White
                ) {
                    Text(stringResource(R.string.manual_entry), modifier = Modifier.padding(horizontal = 8.dp))
                }
                Spacer(Modifier.height(8.dp))
                PDFPicker(onPDFsSelected = { viewModel.analyzePDFs(it) })
            }
        },
        containerColor = com.financeflow.ai.ui.theme.Slate950
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item { 
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        HeaderSection()
                        IconButton(onClick = { showApiKeyDialog = true }) {
                            Icon(androidx.compose.material.icons.Icons.Default.Settings, "API Key", tint = Color.White)
                        }
                    }
                }
                item { SpendingOverview(totalSpending) }
                item { AdviceSection(viewModel.advice.collectAsState().value) }
                item { BentoGridSection(transactions) }
                item {
                    Text(
                        text = stringResource(R.string.recent_activity),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                items(transactions, key = { it.id }) { transaction ->
                    SwipeableTransactionItem(
                        transaction = transaction,
                        onDelete = { viewModel.deleteTransaction(transaction) }
                    ) {
                        TransactionItem(transaction = transaction)
                    }
                }
            }
            // ... (Analyzing Progress remains same)

            // High-Performance Progress Overlay
            if (uiState is DashboardState.Analyzing) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black.copy(alpha = 0.7f)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            color = Indigo600,
                            modifier = Modifier.size(64.dp),
                            strokeWidth = 6.dp
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = stringResource(R.string.analyzing_gains),
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = stringResource(R.string.processing_gemini),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Column {
        Text(
            text = stringResource(R.string.whats_up_boss),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = stringResource(R.string.ai_coach_back),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
fun AdviceSection(advice: String?) {
    advice?.let {
        GlassCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("💡", fontSize = 24.sp)
                Column {
                    Text(
                        text = "AI Coach Says:",
                        style = MaterialTheme.typography.labelLarge,
                        color = Indigo400,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun SpendingOverview(total: Double) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                progress = 0.7f, // Mock progress
                modifier = Modifier.size(140.dp),
                strokeWidth = 12.dp,
                color = Indigo600,
                trackColor = GlassWhite
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "₺${String.format("%.2f", total)}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(text = stringResource(R.string.total_burn), color = Color.LightGray, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun BentoGridSection(transactions: List<Transaction>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GlassCard(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(stringResource(R.string.trends), color = Color.White, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                BarChart(Modifier.fillMaxSize())
            }
        }
        
        Column(
            modifier = Modifier.weight(0.6f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            GlassCard(Modifier.weight(1f).fillMaxWidth()) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(stringResource(R.string.top_cat_tech), color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
            GlassCard(Modifier.weight(1f).fillMaxWidth()) {
                 Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(stringResource(R.string.health_solid), color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .background(GlassWhite)
            .padding(1.dp) // Border simulation
            .background(
                Brush.verticalGradient(
                    listOf(Color.White.copy(alpha = 0.1f), Color.Transparent)
                )
            )
    ) {
        content()
    }
}

@Composable
fun BarChart(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val incomeBars = listOf(0.8f, 0.6f, 0.9f, 0.7f, 0.85f)
        val expenseBars = listOf(0.4f, 0.7f, 0.5f, 0.9f, 0.6f)
        
        val barCount = incomeBars.size
        val barWidth = size.width / (barCount * 3f)
        
        incomeBars.forEachIndexed { index, value ->
            // Income Bar (Teal)
            drawRoundRect(
                color = com.financeflow.ai.ui.theme.Teal400,
                topLeft = Offset(
                    x = barWidth * (index * 3f + 0.5f),
                    y = size.height * (1f - value)
                ),
                size = Size(barWidth, size.height * value),
                cornerRadius = CornerRadius(4.dp.toPx())
            )
            // Expense Bar (Indigo)
            val expValue = expenseBars[index]
            drawRoundRect(
                color = com.financeflow.ai.ui.theme.Indigo500,
                topLeft = Offset(
                    x = barWidth * (index * 3f + 1.6f),
                    y = size.height * (1f - expValue)
                ),
                size = Size(barWidth, size.height * expValue),
                cornerRadius = CornerRadius(4.dp.toPx())
            )
        }
    }
}

@Composable
fun TransactionItem(transaction: Transaction) {
    // Basic implementation of swipable item or simple card for brevity
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = GlassWhite),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text(transaction.merchant, color = Color.White, fontWeight = FontWeight.Bold)
                Text(transaction.category, color = Color.Gray, fontSize = 12.sp)
            }
            val isIncome = transaction.amount < 0
            Text(
                text = "${if (isIncome) "+" else "-"}₺${kotlin.math.abs(transaction.amount)}",
                color = if (isIncome) Teal400 else Color.White,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}
