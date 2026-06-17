package com.anaplan.engineering.azuki.mondex.dsl.action

import com.anaplan.engineering.azuki.mondex.dsl.TransferDetails
import com.anaplan.engineering.azuki.mondex.dsl.TransferOutcome

interface WorldActions : WorldDeclarableActions {
//    fun createWorld(authPurses: Map<String, Pair<ULong, ULong>>)
}

interface WorldDeclarableActions {
    fun thereIsATransfer(transferDetails: TransferDetails, succeed: Boolean = true)
    fun thereIsNoTransfer()
}
