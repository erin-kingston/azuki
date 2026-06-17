package com.anaplan.engineering.azuki.mondex.adapter.declaration.action

import com.anaplan.engineering.azuki.declaration.DeclarableAction
import com.anaplan.engineering.azuki.mondex.adapter.api.AddPersonWithPurseBehaviour
import com.anaplan.engineering.azuki.mondex.adapter.declaration.MondexDeclarationState

open class AddPersonWithPurseDeclarableAction(
    protected val worldName: String = MondexDeclarationState.DEFAULT_WORLD,
    protected val personName: String,
    protected val balance: ULong,
    protected val lost: ULong,
) : AddPersonWithPurseBehaviour(), DeclarableAction<MondexDeclarationState> {

    override fun declare(state: MondexDeclarationState) = state.applyNewPersonWithPurse(worldName, personName, balance, lost)
}
