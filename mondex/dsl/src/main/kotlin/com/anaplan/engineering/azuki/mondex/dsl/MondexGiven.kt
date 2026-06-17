package com.anaplan.engineering.azuki.mondex.dsl

import com.anaplan.engineering.azuki.core.dsl.Given
import com.anaplan.engineering.azuki.core.system.Action
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexActionFactory
import com.anaplan.engineering.azuki.mondex.adapter.api.TransferDetails
import com.anaplan.engineering.azuki.mondex.dsl.action.WorldDeclarableActions
import com.anaplan.engineering.azuki.mondex.dsl.declaration.PurseDeclarations
import com.anaplan.engineering.azuki.mondex.dsl.declaration.WorldDeclarations

class MondexGiven(private val actionFactory: MondexActionFactory<*>) : Given<MondexActionFactory<*>>,
    WorldDeclarableActions, WorldDeclarations, PurseDeclarations{

    private val actionList = mutableListOf<Action>()

    override fun actions(): List<Action> = actionList

    override fun thereIsAPurse(balance: ULong, lost: ULong) {
        actionList.add(actionFactory.purse.create(balance, lost))
    }

    override fun thereIsAWorld(init: WorldBlock.() -> Unit) {
        val authPurses: Map<String, Pair<ULong, ULong>> = HashMap<String, Pair<ULong, ULong>>()
        actionList.add(actionFactory.world.create(authPurses))
        val worldBlock = WorldBlock(actionFactory)
        worldBlock.init()
        actionList.addAll(worldBlock.actions())
    }

    override fun thereIsATransfer(fromPurse: String, toPurse: String, value: Int, succeed: Boolean) {
        require(value >= 0) { "Transfers must be greater than or equal to 0." }
        when (succeed) {
            true -> actionList.add(actionFactory.world.transferOkay(
                TransferDetails(fromPurse, toPurse, value.toULong())
            ))
            false -> actionList.add(actionFactory.world.transferLost(
                TransferDetails(fromPurse, toPurse, value.toULong())
            ))
        }
    }

    override fun thereIsNoTransfer() {
        actionList.add(actionFactory.world.noTransfer())
    }
}
