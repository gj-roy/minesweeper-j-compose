package com.jayasuryat.minesweeperengine.model.grid

import androidx.compose.runtime.Immutable
import com.jayasuryat.minesweeperengine.model.block.GridSize
import com.jayasuryat.minesweeperengine.model.block.Position
import com.jayasuryat.minesweeperengine.model.cell.RawCell

@Immutable
internal data class MineGrid(
    override val gridSize: GridSize,
    override val cells: List<List<RawCell>>,
) : Grid {

    override operator fun get(position: Position): RawCell {
        return cells[position.row][position.column]
    }

    override fun getOrNull(position: Position): RawCell? {
        return kotlin.runCatching { get(position) }.getOrNull()
    }
}