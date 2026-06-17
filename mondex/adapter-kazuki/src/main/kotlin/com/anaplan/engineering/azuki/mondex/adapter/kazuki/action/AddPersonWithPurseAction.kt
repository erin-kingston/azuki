package com.anaplan.engineering.azuki.mondex.adapter.kazuki.action

import com.anaplan.engineering.azuki.mondex.adapter.declaration.action.AddPersonWithPurseDeclarableAction
import com.anaplan.engineering.azuki.mondex.adapter.kazuki.ExecutionEnvironment
import com.anaplan.engineering.azuki.mondex.kazuki.Purse_Module.mk_Purse
import com.anaplan.engineering.kazuki.core.*

class AddPersonWithPurseAction(
    personName: String,
    balance: ULong,
    lost: ULong,
    worldName: String = DEFAULT_WORLD,
) : AddPersonWithPurseDeclarableAction(worldName, personName, balance, lost), KazukiAction {

    override fun act(env: ExecutionEnvironment) {
        env.set(worldName, env.world(worldName).authPurses * mk_Mapping(mk_(personName, mk_Purse(balance, lost))))
    }

    companion object {
        private const val DEFAULT_WORLD = com.anaplan.engineering.azuki.mondex.adapter.declaration.MondexDeclarationState.DEFAULT_WORLD
    }
}
