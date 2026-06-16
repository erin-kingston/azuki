package com.anaplan.engineering.azuki.mondex.adapter.kazuki.action

import com.anaplan.engineering.azuki.core.system.Action
import com.anaplan.engineering.azuki.core.system.ParallelAction
import com.anaplan.engineering.azuki.core.system.UnsupportedAction
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexActionFactory
import com.anaplan.engineering.azuki.mondex.adapter.api.PurseActionFactory
import com.anaplan.engineering.azuki.mondex.adapter.api.WorldActionFactory
import com.anaplan.engineering.azuki.mondex.adapter.kazuki.ExecutionEnvironment

class KazukiActionFactory : MondexActionFactory<KazukiAction> {
    override val purse = KazukiPurseActionFactory
    override val world = KazukiWorldActionFactory

    override fun createParallelAction(actions: List<List<Action>>) =
        KazukiParallelAction(actions.map { it.map(toKazukiAction) })
}

object KazukiPurseActionFactory : PurseActionFactory {
    override fun create(balance: Int, lost: Int) = UnsupportedAction
}

object KazukiWorldActionFactory : WorldActionFactory {
    override fun create(authPurses: Map<String, Pair<Int, Int>>) = CreateWorldAction(authPurses)

    override fun absTransfer(transferDetails: Triple<String, String, Int>) =
        AbsTransferAction(transferDetails)

    override fun absIgnore() = AbsIgnoreAction()
}

interface KazukiAction : Action {
    fun act(env: ExecutionEnvironment)
}

class KazukiParallelAction(actions: List<List<KazukiAction>>) : ParallelAction<KazukiAction>(actions), KazukiAction {
    override fun act(env: ExecutionEnvironment) {
        runActionsConcurrently { action -> action.act(env) }
    }
}

val toKazukiAction: (Action) -> KazukiAction = {
    it as? KazukiAction ?: throw IllegalArgumentException("Invalid action: $it")
}
