package kr.loner.korbit.ui.util.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kr.loner.korbit.R

@Composable
fun LoadingView(modifier: Modifier) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(
    message: String,
    modifier: Modifier,
    onClickRetry: () -> Unit
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            maxLines = 1,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Red,
            modifier = Modifier.padding(4.dp)
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = stringResource(R.string.retry))
        }

    }
}