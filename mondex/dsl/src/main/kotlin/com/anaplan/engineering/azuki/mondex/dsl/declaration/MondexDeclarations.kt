package com.anaplan.engineering.azuki.mondex.dsl.declaration

import com.anaplan.engineering.azuki.mondex.adapter.api.MondexActionFactory
import com.anaplan.engineering.azuki.mondex.dsl.PurseBlock
import com.anaplan.engineering.azuki.mondex.dsl.WorldBlock

interface PurseDeclarations {
    fun thereIsAPurse(balance: ULong, lost: ULong)
    fun thereIsAPurse(balance: ULong, lost: ULong, init: PurseBlock.() -> Unit)
}

interface WorldDeclarations {
    fun thereIsAWorld(authPurses: Map<String, Pair<ULong, ULong>>)
    fun thereIsAWorld(authPurses: Map<String, Pair<ULong, ULong>>, init: WorldBlock.() -> Unit)
}
