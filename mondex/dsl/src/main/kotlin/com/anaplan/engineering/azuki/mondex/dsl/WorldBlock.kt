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

    fun personWithPurse(personName: String, balance: Int, lost: Int) {
        require(balance >= 0 && lost >= 0) { "Purse values must be zero or greater than zero." }
        actionList.add(actionFactory.world.addPersonWithPurse(personName, balance.toULong(), lost.toULong()))
    }
}
