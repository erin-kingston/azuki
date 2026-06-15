package com.anaplan.engineering.azuki.mondex.dsl

import com.anaplan.engineering.azuki.core.dsl.ScenarioDsl
import com.anaplan.engineering.azuki.core.system.Action
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexActionFactory

@ScenarioDsl
class WorldBlock(
    private val authPurses: Map<String, Pair<Int, Int>>,
    private val actionFactory: MondexActionFactory<*>,
) {
    private val actionList = mutableListOf<Action>()

    fun actions(): List<Action> = actionList

    fun absTransfer(transferDetails: Triple<String, String, Int>) {
        actionList.add(actionFactory.world.absTransfer(transferDetails))
    }

    fun absIgnore() {
        actionList.add(actionFactory.world.absIgnore())
    }
}
