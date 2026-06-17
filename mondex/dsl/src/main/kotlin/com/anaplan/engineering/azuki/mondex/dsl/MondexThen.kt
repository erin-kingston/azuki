package com.anaplan.engineering.azuki.mondex.dsl

import com.anaplan.engineering.azuki.core.dsl.Then
import com.anaplan.engineering.azuki.core.system.Check
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexCheckFactory
import com.anaplan.engineering.azuki.mondex.dsl.check.MondexChecks

class MondexThen(private val checkFactory: MondexCheckFactory) : Then<MondexCheckFactory>,
    MondexChecks {

    private val checkList = mutableListOf<Check>()

    override fun checks() = checkList

    override fun purseExists(personName: String) {
        checkList.add(checkFactory.purseExists(personName, true))
    }

    override fun purseExists(personName: String, purse: Purse) {
        checkList.add(checkFactory.purseExists(personName, purse.balance.toULong(), purse.lost.toULong(), true))
    }

    override fun worldExists(init: WorldCheckBlock.() -> Unit) {
        val worldCheckBlock = WorldCheckBlock(checkFactory)
        worldCheckBlock.init()
        checkList.addAll(worldCheckBlock.checks())
        checkList.add(checkFactory.worldExists(worldCheckBlock.getAuthPurses(), true))
    }
}
