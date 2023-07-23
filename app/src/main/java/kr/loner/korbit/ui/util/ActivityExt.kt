package kr.loner.korbit.ui.util

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import kr.loner.korbit.ui.theme.KobitTheme

fun ComponentActivity.setThemeContent(content: @Composable () -> Unit) = setContent {
    KobitTheme(content = content)
}