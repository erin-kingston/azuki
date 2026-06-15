package com.anaplan.engineering.azuki.mondex.dsl

import com.anaplan.engineering.azuki.core.dsl.Given
import com.anaplan.engineering.azuki.core.system.Action
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexActionFactory
import com.anaplan.engineering.azuki.mondex.dsl.action.WorldDeclarableActions
import com.anaplan.engineering.azuki.mondex.dsl.declaration.WorldDeclarations

class MondexGiven(private val actionFactory: MondexActionFactory<*>) : Given<MondexActionFactory<*>>,
    WorldDeclarableActions, WorldDeclarations{

    private val actionList = mutableListOf<Action>()

    override fun actions(): List<Action> = actionList

    override fun thereIsAWorld(authPurses: Map<String, Pair<Int, Int>>) {
        actionList.add(actionFactory.world.create(authPurses))
    }

    override fun thereIsAWorld(authPurses: Map<String, Pair<Int, Int>>, init: WorldBlock.() -> Unit) {
        actionList.add(actionFactory.world.create(authPurses))
        val worldBlock = WorldBlock(authPurses, actionFactory)
        worldBlock.init()
        actionList.addAll(worldBlock.actions())
    }

    override fun absTransfer(transferDetails: Triple<String, String, Int>) {
        actionList.add(actionFactory.world.absTransfer(transferDetails))
    }

    override fun absIgnore() {
        actionList.add(actionFactory.world.absIgnore())
    }
}
