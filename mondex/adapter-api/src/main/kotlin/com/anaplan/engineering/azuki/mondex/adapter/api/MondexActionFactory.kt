package com.anaplan.engineering.azuki.mondex.adapter.api

import com.anaplan.engineering.azuki.core.system.Action
import com.anaplan.engineering.azuki.core.system.ActionFactory
import com.anaplan.engineering.azuki.core.system.ParallelActionFactory


interface MondexActionFactory<out A: Action > : ActionFactory, ParallelActionFactory<A> {
    val purse: PurseActionFactory
    val world: WorldActionFactory
}

interface PurseActionFactory {
    fun create(balance: ULong, lost: ULong): Action
}

interface WorldActionFactory {
    fun create(authPurses: Map<String, Pair<ULong, ULong>>): Action
    fun transferOkay(transferDetails: Triple<String, String, ULong>): Action
    fun transferLost(transferDetails: Triple<String, String, ULong>): Action
    fun noTransfer(): Action
    fun addPersonWithPurse(personName: String, purse: Pair<ULong, ULong>): Action
}
