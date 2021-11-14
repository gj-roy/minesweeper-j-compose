package com.jayasuryat.minesweeperui.composable.grid

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import com.jayasuryat.minesweeperengine.model.block.GridSize
import com.jayasuryat.minesweeperengine.model.block.Position
import com.jayasuryat.minesweeperengine.model.cell.RawCell
import com.jayasuryat.minesweeperui.composable.cell.RawCell
import com.jayasuryat.minesweeperui.composable.event.MinefieldActionsListener
import com.jayasuryat.minesweeperui.composable.util.dp
import com.jayasuryat.minesweeperui.composable.util.floatValue

@Composable
internal fun MineGrid(
    modifier: Modifier,
    gridInfo: GridLayoutInformation,
    actionListener: MinefieldActionsListener,
) {

    BoxWithConstraints(
        modifier = modifier,
    ) {

        val width = maxWidth
        val gridSize = gridInfo.gridSize

        val cellSize = getCellSize(
            gridSize = gridSize,
            width = width,
        )

        InverseClippedBox(
            parentSize = Size(width = maxWidth.floatValue(),
                height = maxHeight.floatValue()),
            contentSize = Size(
                width = cellSize * gridSize.columns,
                height = cellSize * gridSize.rows,
            ),
            padding = Size(width = 32f, height = 32f),
            innerInset = Size(width = 1f, height = 1f)
        )

        Grid(
            parentHeight = maxHeight,
            gridInfo = gridInfo,
            actionListener = actionListener,
            cellSize = cellSize,
        )
    }
}

@Composable
private fun Grid(
    parentHeight: Dp,
    gridInfo: GridLayoutInformation,
    actionListener: MinefieldActionsListener,
    cellSize: Float,
) {

    val centerOffset = (parentHeight / 2).floatValue() - (cellSize * gridInfo.gridSize.rows / 2)

    val overlap = 1f
    val overlappingCellSize = cellSize + overlap

    gridInfo.cells.forEach { cellData ->

        val (cell: State<RawCell>, position: Position) = cellData
        val offset = position.asOffset(cellSize = cellSize)

        RawCell(
            modifier = Modifier
                .size(overlappingCellSize.dp())
                .graphicsLayer {
                    translationX = offset.x
                    translationY = offset.y + centerOffset
                },
            cellState = cell,
            actionListener = actionListener,
        )
    }
}

@Composable
@Stable
private fun getCellSize(
    gridSize: GridSize,
    width: Dp,
): Float {
    val defCellWidth = width / gridSize.columns
    return defCellWidth.floatValue()
}

@Stable
private fun Position.asOffset(
    cellSize: Float,
): Offset {
    val xOff = cellSize * this.column
    val yOff = cellSize * this.row
    return Offset(x = xOff, y = yOff)
}