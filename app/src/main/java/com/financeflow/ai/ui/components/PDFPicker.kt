package com.financeflow.ai.ui.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.financeflow.ai.R
import com.financeflow.ai.ui.theme.Indigo600

@Composable
fun PDFPicker(
    onPDFsSelected: (List<Uri>) -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenMultipleDocuments(),
        onResult = { uris ->
            if (uris.isNotEmpty()) {
                onPDFsSelected(uris)
            }
        }
    )

    ExtendedFloatingActionButton(
        onClick = { launcher.launch(arrayOf("application/pdf")) },
        icon = { Icon(Icons.Default.Add, contentDescription = null, tint = Color.White) },
        text = { Text(stringResource(R.string.feed_ai_boss), color = Color.White) },
        containerColor = Indigo600,
        modifier = Modifier
            .padding(16.dp)
    )
}
