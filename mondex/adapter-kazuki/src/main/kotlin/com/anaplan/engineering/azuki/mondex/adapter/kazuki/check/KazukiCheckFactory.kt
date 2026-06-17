package com.anaplan.engineering.azuki.mondex.adapter.kazuki.check

import com.anaplan.engineering.azuki.core.system.Check
import com.anaplan.engineering.azuki.core.system.unsupportedBehavior
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexCheckFactory
import com.anaplan.engineering.azuki.mondex.adapter.kazuki.ExecutionEnvironment

class KazukiCheckFactory : MondexCheckFactory {

    override fun purseExists(personName: String, result: Boolean) =
        PurseExistsCheck(personName, result)

    override fun purseExists(personName: String, balance: ULong, lost: ULong, result: Boolean) =
        PurseExistsWithValuesCheck(personName, balance, lost, result)

    override fun worldExists(authPurses: Map<String, Pair<ULong, ULong>>, result: Boolean) =
        WorldExistsCheck(authPurses, result)

    override fun systemValid() = object : KazukiCheck {
        override val behavior = unsupportedBehavior
        override fun check(env: ExecutionEnvironment) = true
    }
}

interface KazukiCheck : Check {
    fun check(env: ExecutionEnvironment): Boolean
}
