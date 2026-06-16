package com.anaplan.engineering.azuki.mondex.dsl.action

interface WorldActions : WorldDeclarableActions {
    fun createWorld(authPurses: Map<String, Pair<ULong, ULong>>)
}

interface WorldDeclarableActions {
    fun absTransfer(transferDetails: Triple<String, String, ULong>)
    fun absIgnore()
}
