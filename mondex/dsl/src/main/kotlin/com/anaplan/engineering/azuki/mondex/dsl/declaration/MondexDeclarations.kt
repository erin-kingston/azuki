package com.anaplan.engineering.azuki.mondex.dsl.declaration

import com.anaplan.engineering.azuki.mondex.adapter.api.MondexActionFactory
import com.anaplan.engineering.azuki.mondex.dsl.PurseBlock
import com.anaplan.engineering.azuki.mondex.dsl.WorldBlock

interface PurseDeclarations {
    fun thereIsAPurse(balance: Int, lost: Int)
    fun thereIsAPurse(balance: Int, lost: Int, init: PurseBlock.() -> Unit)
}

interface WorldDeclarations {
    fun thereIsAWorld(authPurses: Map<String, Pair<Int, Int>>)
    fun thereIsAWorld(authPurses: Map<String, Pair<Int, Int>>, init: WorldBlock.() -> Unit)
}
