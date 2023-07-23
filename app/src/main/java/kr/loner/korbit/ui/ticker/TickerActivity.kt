package kr.loner.korbit.ui.ticker

import android.os.Bundle
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import kr.loner.korbit.ui.ticker.screen.TickerScreen
import kr.loner.korbit.ui.util.setThemeContent

@AndroidEntryPoint
class TickerActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setThemeContent { TickerScreen() }
    }

}

