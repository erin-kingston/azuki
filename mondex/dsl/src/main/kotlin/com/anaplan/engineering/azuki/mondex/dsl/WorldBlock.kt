package com.anaplan.engineering.azuki.mondex.dsl

import com.anaplan.engineering.azuki.core.dsl.ScenarioDsl
import com.anaplan.engineering.azuki.core.system.Action
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexActionFactory

@ScenarioDsl
class WorldBlock(
    private val actionFactory: MondexActionFactory<*>,
) {
    private val actionList = mutableListOf<Action>()

    fun actions(): List<Action> = actionList

    fun personWithPurse(name: String, purse: Pair<ULong, ULong>) {
        actionList.add(actionFactory.world.addPersonWithPurse(name, purse))
    }
}
