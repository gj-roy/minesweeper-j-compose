package com.jayasuryat.minesweeperui.composable.cell.revealed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jayasuryat.minesweeperui.composable.component.LogCompositions

@Composable
internal fun EmptyCell(
    modifier: Modifier = Modifier,
) {

    LogCompositions(name = "EmptyCell")

    Spacer(modifier = modifier
        .aspectRatio(1f)
        .clipToBounds()
        .background(color = Color.Black)
    )
}

@Preview(heightDp = 600, widthDp = 600)
@Preview(heightDp = 60, widthDp = 60)
@Composable
private fun Preview() {
    EmptyCell(
        modifier = Modifier.fillMaxSize(),
    )
}