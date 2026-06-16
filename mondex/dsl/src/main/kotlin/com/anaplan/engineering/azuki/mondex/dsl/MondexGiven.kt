package com.anaplan.engineering.azuki.mondex.dsl

import com.anaplan.engineering.azuki.core.dsl.Given
import com.anaplan.engineering.azuki.core.system.Action
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexActionFactory
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

    override fun thereIsAPurse(balance: ULong, lost: ULong, init: PurseBlock.() -> Unit) {
        actionList.add(actionFactory.purse.create(balance, lost))
        val purseBlock = PurseBlock(balance, lost, actionFactory)
        purseBlock.init()
        actionList.addAll(purseBlock.actions())
    }

    override fun thereIsAWorld(authPurses: Map<String, Pair<ULong, ULong>>) {
        actionList.add(actionFactory.world.create(authPurses))
    }

    override fun thereIsAWorld(authPurses: Map<String, Pair<ULong, ULong>>, init: WorldBlock.() -> Unit) {
        actionList.add(actionFactory.world.create(authPurses))
        val worldBlock = WorldBlock(authPurses, actionFactory)
        worldBlock.init()
        actionList.addAll(worldBlock.actions())
    }

    override fun absTransfer(transferDetails: Triple<String, String, ULong>) {
        actionList.add(actionFactory.world.absTransfer(transferDetails))
    }

    override fun absIgnore() {
        actionList.add(actionFactory.world.absIgnore())
    }
}
