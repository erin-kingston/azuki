package com.anaplan.engineering.azuki.mondex.adapter.declaration

import com.anaplan.engineering.azuki.declaration.DeclarationState
import com.anaplan.engineering.azuki.mondex.adapter.api.TransferDetails
import com.anaplan.engineering.azuki.mondex.adapter.declaration.declaration.WorldDeclaration
import com.anaplan.engineering.azuki.mondex.adapter.declaration.declaration.WorldOperation

class MondexDeclarationState : DeclarationState() {

    fun declareWorld(worldName: String, authPurses: Map<String, Pair<ULong, ULong>>) {
        checkForDuplicate(worldName)
        declarations[worldName] = WorldDeclaration(worldName, authPurses, emptyList(), standalone = true)
    }

    fun applyTransfer(worldName: String, transferDetails: TransferDetails) {
        val world = getDeclaration<WorldDeclaration>(worldName)
        declarations[worldName] = world.copy(
            operations = world.operations + WorldOperation.Transfer(
                transferDetails.fromPurse,
                transferDetails.toPurse,
                transferDetails.value,
            ),
        )
    }

    fun applyIgnore(worldName: String) {
        val world = getDeclaration<WorldDeclaration>(worldName)
        declarations[worldName] = world.copy(
            operations = world.operations + WorldOperation.Ignore,
        )
    }

    fun applyNewPersonWithPurse(worldName: String, personName: String, balance: ULong, lost: ULong) {
        val world = getDeclaration<WorldDeclaration>(worldName)
        declarations[worldName] = world.copy(
            operations = (world.operations + WorldOperation.AddPersonWithPurse(personName, balance, lost))
        )
    }

    companion object {
        const val DEFAULT_WORLD = "world"
    }
}
