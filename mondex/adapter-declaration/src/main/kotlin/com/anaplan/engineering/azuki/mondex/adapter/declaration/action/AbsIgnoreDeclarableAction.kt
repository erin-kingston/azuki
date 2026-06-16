package com.anaplan.engineering.azuki.mondex.adapter.declaration.action

import com.anaplan.engineering.azuki.declaration.DeclarableAction
import com.anaplan.engineering.azuki.mondex.adapter.api.AbsIgnoreBehaviour
import com.anaplan.engineering.azuki.mondex.adapter.declaration.MondexDeclarationState

open class AbsIgnoreDeclarableAction(
    protected val worldName: String = MondexDeclarationState.DEFAULT_WORLD,
) : AbsIgnoreBehaviour(), DeclarableAction<MondexDeclarationState> {

    override fun declare(state: MondexDeclarationState) = state.applyIgnore(worldName)
}
