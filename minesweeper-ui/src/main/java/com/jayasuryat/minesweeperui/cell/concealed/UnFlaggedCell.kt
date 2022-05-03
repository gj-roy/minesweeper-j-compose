/*
 * Copyright 2022 Jaya Surya Thotapalli
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jayasuryat.minesweeperui.cell.concealed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import com.jayasuryat.minesweeperengine.model.block.Position
import com.jayasuryat.minesweeperengine.model.cell.MineCell
import com.jayasuryat.minesweeperengine.model.cell.RawCell
import com.jayasuryat.minesweeperui.action.CellInteraction
import com.jayasuryat.minesweeperui.action.CellInteractionListener
import com.jayasuryat.minesweeperui.action.NoOpInteractionListener
import com.jayasuryat.util.LogCompositions

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun UnFlaggedCell(
    modifier: Modifier = Modifier,
    cell: RawCell.UnrevealedCell.UnFlaggedCell,
    actionListener: CellInteractionListener,
) {

    LogCompositions(name = "UnFlaggedCell")

    val haptic = LocalHapticFeedback.current

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(color = MaterialTheme.colors.primary)
            .combinedClickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = {
                    val action = CellInteraction.OnCellClicked(cell)
                    actionListener.action(action)
                },
                onLongClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    val action = CellInteraction.OnCellLongPressed(cell)
                    actionListener.action(action)
                },
            ),
    )
}

@Preview(heightDp = 600, widthDp = 600)
@Preview(heightDp = 60, widthDp = 60)
@Composable
private fun Preview() {

    val cell = RawCell.UnrevealedCell.UnFlaggedCell(
        cell = MineCell.ValuedCell.EmptyCell(position = Position.zero())
    )

    UnFlaggedCell(
        modifier = Modifier.fillMaxSize(),
        cell = cell,
        actionListener = NoOpInteractionListener,
    )
}
