package com.anaplan.engineering.azuki.mondex.dsl.check

import com.anaplan.engineering.azuki.mondex.dsl.Purse
import com.anaplan.engineering.azuki.mondex.dsl.WorldCheckBlock

interface MondexChecks {
    fun purseExists(personName: String)
    fun purseExists(personName: String, purse: Purse)
    fun worldExists(init: WorldCheckBlock.() -> Unit)
}
