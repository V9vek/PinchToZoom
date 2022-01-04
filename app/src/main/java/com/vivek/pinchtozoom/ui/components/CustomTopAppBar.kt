package com.vivek.pinchtozoom.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar

@Composable
fun CustomTopAppBar(
    isZooming: Boolean
) {
    AnimatedVisibility(
        visible = isZooming.not(),
        enter = slideInVertically() + expandVertically(),
        exit = slideOutVertically() + shrinkVertically()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Instagram",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            backgroundColor = Color.White.copy(0.95f),
            contentPadding = rememberInsetsPaddingValues(
                LocalWindowInsets.current.statusBars,
                applyBottom = false,
                applyStart = false,
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}