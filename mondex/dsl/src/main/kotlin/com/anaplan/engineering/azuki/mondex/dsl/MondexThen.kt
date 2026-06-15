package com.anaplan.engineering.azuki.mondex.dsl

import com.anaplan.engineering.azuki.core.dsl.Then
import com.anaplan.engineering.azuki.core.system.Check
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexCheckFactory
import com.anaplan.engineering.azuki.mondex.dsl.check.MondexChecks

class MondexThen(private val checkFactory: MondexCheckFactory) : Then<MondexCheckFactory>,
    MondexChecks {

    private val checkList = mutableListOf<Check>()

    override fun checks() = checkList

    override fun purseExists(purseName: String) {
        checkList.add(checkFactory.purseExists(purseName, true))
    }
}
