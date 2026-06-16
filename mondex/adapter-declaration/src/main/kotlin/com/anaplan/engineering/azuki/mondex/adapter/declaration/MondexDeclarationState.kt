package com.anaplan.engineering.azuki.mondex.adapter.declaration

import com.anaplan.engineering.azuki.declaration.DeclarationState
import com.anaplan.engineering.azuki.mondex.adapter.declaration.declaration.WorldDeclaration
import com.anaplan.engineering.azuki.mondex.adapter.declaration.declaration.WorldOperation

class MondexDeclarationState : DeclarationState() {

    fun declareWorld(worldName: String, authPurses: Map<String, Pair<ULong, ULong>>) {
        checkForDuplicate(worldName)
        declarations[worldName] = WorldDeclaration(worldName, authPurses, emptyList(), standalone = true)
    }

    fun applyTransfer(worldName: String, transferDetails: Triple<String, String, ULong>) {
        val world = getDeclaration<WorldDeclaration>(worldName)
        declarations[worldName] = world.copy(
            operations = world.operations + WorldOperation.Transfer(
                transferDetails.first,
                transferDetails.second,
                transferDetails.third,
            ),
        )
    }

    fun applyIgnore(worldName: String) {
        val world = getDeclaration<WorldDeclaration>(worldName)
        declarations[worldName] = world.copy(
            operations = world.operations + WorldOperation.Ignore,
        )
    }

    companion object {
        const val DEFAULT_WORLD = "world"
    }
}
