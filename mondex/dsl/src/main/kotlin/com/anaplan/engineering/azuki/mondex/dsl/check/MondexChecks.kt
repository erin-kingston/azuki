package com.anaplan.engineering.azuki.mondex.dsl.check

import com.anaplan.engineering.azuki.mondex.dsl.WorldBlock

interface MondexChecks {
    fun purseExists(purseName: String)
    fun purseExists(purseName: String, balance: ULong, lost: ULong)
    fun worldExists(authPurses: Map<String, Pair<ULong, ULong>>)
}
