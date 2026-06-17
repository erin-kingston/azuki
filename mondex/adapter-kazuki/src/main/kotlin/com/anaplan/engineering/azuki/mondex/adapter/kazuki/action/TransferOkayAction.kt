package com.anaplan.engineering.azuki.mondex.adapter.kazuki.action

import com.anaplan.engineering.azuki.mondex.adapter.declaration.action.TransferDeclarableAction
import com.anaplan.engineering.azuki.mondex.adapter.kazuki.ExecutionEnvironment
import com.anaplan.engineering.azuki.mondex.adapter.kazuki.toTransferDetails
import com.anaplan.engineering.azuki.mondex.kazuki.Transfer_Module.mk_Transfer

class TransferOkayAction(
    transferDetails: Triple<String, String, ULong>,
    worldName: String = DEFAULT_WORLD,
) : TransferDeclarableAction(transferDetails, worldName), KazukiAction {

    override fun act(env: ExecutionEnvironment) {
        val td = transferDetails.toTransferDetails()
        val input = mk_Transfer(td)
        env.set(worldName, env.world(worldName).functions.abstractTransferOkayTD(input, td))
    }

    companion object {
        private const val DEFAULT_WORLD = com.anaplan.engineering.azuki.mondex.adapter.declaration.MondexDeclarationState.DEFAULT_WORLD
    }
}
