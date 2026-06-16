package com.anaplan.engineering.azuki.mondex.adapter.kazuki.check

import com.anaplan.engineering.azuki.mondex.adapter.api.PurseExistsBehaviour
import com.anaplan.engineering.azuki.mondex.adapter.declaration.MondexDeclarationState
import com.anaplan.engineering.azuki.mondex.adapter.kazuki.ExecutionEnvironment

class PurseExistsCheck(
    private val purseName: String,
    private val expected: Boolean,
) : PurseExistsBehaviour(), KazukiCheck {

    override fun check(env: ExecutionEnvironment): Boolean {
        val exists = purseName in env.world(MondexDeclarationState.DEFAULT_WORLD).authPurses.dom
        return exists == expected
    }
}
