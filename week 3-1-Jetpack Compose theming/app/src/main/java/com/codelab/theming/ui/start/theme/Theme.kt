package com.codelab.theming.ui.start.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.codelab.theming.ui.finish.theme.*

// 다크모드가 아닐때 사용될 색깔
private val LightColors2 = lightColors(
    primary = Red700_,
    primaryVariant = Red900_,
    onPrimary = Color.White,
    secondary = Red700_,
    secondaryVariant = Red900_,
    onSecondary = Color.White,
    error = Red800_
)


// 다크모드일때 사용될 색깔
private val DarkColors2 = darkColors(
    primary = Red300_,
    primaryVariant = Red700_,
    onPrimary = Color.Black,
    secondary = Red300_,
    onSecondary = Color.Black,
    error = Red200_
)

@Composable
fun JetnewsTheme2(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColors2 else LightColors2,
        typography = JetnewsTypography2,
        shapes = JetnewsShapes2,
        content = content
    )
}
