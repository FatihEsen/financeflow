package com.financeflow.ai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import com.financeflow.ai.ui.dashboard.DashboardScreen
import com.financeflow.ai.ui.dashboard.DashboardViewModel
import com.financeflow.ai.ui.theme.FinanceFlowColorScheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.viewmodel.compose.viewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = FinanceFlowColorScheme) {
                val viewModel: DashboardViewModel = viewModel()
                DashboardScreen(viewModel = viewModel)
            }
        }
    }
}
