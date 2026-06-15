package com.anaplan.engineering.azuki.mondex.dsl.action

interface WorldActions : WorldDeclarableActions {
    fun createWorld(authPurses: Map<String, Pair<Int, Int>>)
}

interface WorldDeclarableActions {
    fun absTransfer(transferDetails: Triple<String, String, Int>)
    fun absIgnore()
}
