package com.anaplan.engineering.azuki.mondex.dsl

import com.anaplan.engineering.azuki.core.dsl.ScenarioDsl
import com.anaplan.engineering.azuki.core.system.Check
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexCheckFactory

@ScenarioDsl
class WorldCheckBlock(
    private val checkFactory: MondexCheckFactory
) {
    private val checkList = mutableListOf<Check>()
    private val authPurses = HashMap<String, Pair<ULong, ULong>>()

    fun checks(): List<Check> = checkList
    fun getAuthPurses(): Map<String, Pair<ULong, ULong>> = authPurses

    fun personWithPurse(personName: String, purse: Purse) {
        checkList.add(checkFactory.purseExists(personName, purse.balance.toULong(), purse.lost.toULong(), true))
        authPurses[personName] = (purse.balance.toULong() to purse.lost.toULong())
    }

}
