package com.anaplan.engineering.azuki.mondex.dsl.declaration

import com.anaplan.engineering.azuki.mondex.dsl.WorldBlock

interface PurseDeclarations {
    fun thereIsAPurse(balance: ULong, lost: ULong)
}

interface WorldDeclarations {
    fun thereIsAWorld(authPurses: Map<String, Pair<ULong, ULong>>)
    fun thereIsAWorld(init: WorldBlock.() -> Unit)
}
