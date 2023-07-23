package kr.loner.korbit.ui.ticker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import kr.loner.korbit.R
import kr.loner.korbit.ui.theme.KobitTheme
import kr.loner.korbit.ui.ticker.screen.TickerScreen
import kr.loner.korbit.ui.util.UiState
import kr.loner.korbit.ui.util.compose.ErrorView
import kr.loner.korbit.ui.util.compose.LoadingView
import kr.loner.korbit.ui.util.data

@AndroidEntryPoint
class TickerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KobitTheme {
                TickerScreen()
            }
        }
    }

}

