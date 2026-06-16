package com.anaplan.engineering.azuki.mondex.adapter.declaration.action

import com.anaplan.engineering.azuki.declaration.DeclarableAction
import com.anaplan.engineering.azuki.mondex.adapter.api.AbsTransferBehaviour
import com.anaplan.engineering.azuki.mondex.adapter.declaration.MondexDeclarationState

open class AbsTransferDeclarableAction(
    protected val transferDetails: Triple<String, String, Int>,
    protected val worldName: String = MondexDeclarationState.DEFAULT_WORLD,
) : AbsTransferBehaviour(), DeclarableAction<MondexDeclarationState> {

    override fun declare(state: MondexDeclarationState) = state.applyTransfer(worldName, transferDetails)
}
