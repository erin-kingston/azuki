package com.anaplan.engineering.azuki.mondex.adapter.api

import com.anaplan.engineering.azuki.core.system.Action
import com.anaplan.engineering.azuki.core.system.ActionFactory
import com.anaplan.engineering.azuki.core.system.ParallelActionFactory
import com.anaplan.engineering.azuki.core.system.UnsupportedAction


interface MondexActionFactory<out A: Action > : ActionFactory, ParallelActionFactory<A> {
    val purse: PurseActionFactory
    val world: WorldActionFactory
}

interface PurseActionFactory {
    fun create(balance: Int, lost: Int): Action = UnsupportedAction
}

interface WorldActionFactory {
    fun create(authPurses: Map<String, Pair<Int, Int>>) = UnsupportedAction
    fun absTransfer(transferDetails: Triple<String, String, Int>) = UnsupportedAction
    fun absIgnore() = UnsupportedAction
}
