package com.anaplan.engineering.azuki.mondex.dsl.check

import com.anaplan.engineering.azuki.mondex.dsl.WorldCheckBlock

interface MondexChecks {
    fun purseExists(personName: String)
    fun purseExists(personName: String, balance: Int, lost: Int)
    fun worldExists(init: WorldCheckBlock.() -> Unit)
}
