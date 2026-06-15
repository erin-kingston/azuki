package com.anaplan.engineering.azuki.mondex.adapter.api

import com.anaplan.engineering.azuki.core.system.Check
import com.anaplan.engineering.azuki.core.system.CheckFactory
import com.anaplan.engineering.azuki.core.system.UnsupportedCheck

interface MondexCheckFactory : CheckFactory {
    fun purseExists(purseName: String, result: Boolean): Check = UnsupportedCheck
}
