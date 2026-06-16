package com.anaplan.engineering.azuki.mondex.adapter.api

import com.anaplan.engineering.azuki.core.system.Check
import com.anaplan.engineering.azuki.core.system.CheckFactory

interface MondexCheckFactory : CheckFactory {
    fun purseExists(purseName: String, result: Boolean): Check
    fun purseExists(purseName: String, balance: ULong, lost: ULong, result: Boolean): Check
    fun worldExists(authPurses: Map<String, Pair<ULong, ULong>>, result: Boolean): Check
}
