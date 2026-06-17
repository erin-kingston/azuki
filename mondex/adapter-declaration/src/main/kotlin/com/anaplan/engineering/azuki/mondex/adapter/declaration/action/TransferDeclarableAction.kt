package com.anaplan.engineering.azuki.mondex.adapter.declaration.action

import com.anaplan.engineering.azuki.declaration.DeclarableAction
import com.anaplan.engineering.azuki.mondex.adapter.api.AbsTransferBehaviour
import com.anaplan.engineering.azuki.mondex.adapter.api.TransferDetails
import com.anaplan.engineering.azuki.mondex.adapter.declaration.MondexDeclarationState

open class TransferDeclarableAction(
    protected val transferDetails: TransferDetails,
    protected val worldName: String = MondexDeclarationState.DEFAULT_WORLD,
) : AbsTransferBehaviour(), DeclarableAction<MondexDeclarationState> {

    override fun declare(state: MondexDeclarationState) = state.applyTransfer(worldName, transferDetails)
}
