package com.anaplan.engineering.azuki.mondex.adapter.kazuki.check

import com.anaplan.engineering.azuki.mondex.adapter.api.PurseExistsBehaviour
import com.anaplan.engineering.azuki.mondex.adapter.declaration.MondexDeclarationState
import com.anaplan.engineering.azuki.mondex.adapter.kazuki.ExecutionEnvironment
import com.anaplan.engineering.azuki.mondex.kazuki.Purse

class PurseExistsCheck(
    private val purseName: String,
    private val expected: Boolean,
) : PurseExistsBehaviour(), KazukiCheck {

    override fun check(env: ExecutionEnvironment): Boolean {
        val exists = purseName in env.world(MondexDeclarationState.DEFAULT_WORLD).authPurses.dom
        return exists == expected
    }
}

class PurseExistsWithValuesCheck(
    private val purseName: String,
    private val balance: ULong,
    private val lost: ULong,
    private val expected: Boolean,
) : PurseExistsBehaviour(), KazukiCheck {

    override fun check(env: ExecutionEnvironment): Boolean {
        val exists = purseName in env.world(MondexDeclarationState.DEFAULT_WORLD).authPurses.dom
            && env.world(MondexDeclarationState.DEFAULT_WORLD).authPurses[purseName].balance == balance
            && env.world(MondexDeclarationState.DEFAULT_WORLD).authPurses[purseName].lost == lost
        return exists == expected
    }
}
