package com.anaplan.engineering.azuki.mondex.dsl

import com.anaplan.engineering.azuki.core.dsl.ScenarioDsl
import com.anaplan.engineering.azuki.core.system.Check
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexCheckFactory

@ScenarioDsl
class PurseCheckBlock(
    private val personName: String,
    private val checkFactory: MondexCheckFactory,
) {
    private val checkList = mutableListOf<Check>()

    fun checks(): List<Check> = checkList

    fun hasBalance(balance: Int) {
        require(balance >= 0) { "balance must be greater than or equal to zero" }
    }

    fun hasLosses(lost: Int) {
        require(lost >= 0) { "lost must be greater than or equal to zero" }
    }
}
