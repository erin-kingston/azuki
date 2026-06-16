package com.anaplan.engineering.azuki.mondex.dsl

import com.anaplan.engineering.azuki.core.dsl.ParallelWhen
import com.anaplan.engineering.azuki.core.dsl.When
import com.anaplan.engineering.azuki.core.system.Action
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexActionFactory
import com.anaplan.engineering.azuki.mondex.dsl.action.PurseActions
import com.anaplan.engineering.azuki.mondex.dsl.action.WorldActions

class MondexWhen(private val actionFactory: MondexActionFactory<*>) :
    When<MondexActionFactory<*>>,
    ParallelWhen<MondexActionFactory<*>, MondexWhen>,
    WorldActions,
    PurseActions {

    private val actionList = mutableListOf<Action>()

    override fun actions() = actionList

    override fun createPurse(balance: Int, lost: Int) {
        actionList.add(actionFactory.purse.create(balance, lost))
    }

    override fun createWorld(authPurses: Map<String, Pair<Int, Int>>) {
        actionList.add(actionFactory.world.create(authPurses))
    }

    override fun absTransfer(transferDetails: Triple<String, String, Int>) {
        actionList.add(actionFactory.world.absTransfer(transferDetails))
    }

    override fun absIgnore() {
        actionList.add(actionFactory.world.absIgnore())
    }

    override fun parallel(vararg fns: MondexWhen.() -> Unit) {
        actionList.add(actionFactory.createParallelAction(
            fns.map { fn ->
                val w = MondexWhen(actionFactory)
                w.fn()
                w.actions()
            }
        ))
    }
}
