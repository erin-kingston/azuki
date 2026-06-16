package com.anaplan.engineering.azuki.mondex.adapter.kazuki

import com.anaplan.engineering.azuki.mondex.kazuki.World
import com.anaplan.engineering.azuki.mondex.kazuki.Purse_Module.mk_Purse
import com.anaplan.engineering.azuki.mondex.kazuki.TransferDetails_Module.mk_TransferDetails
import com.anaplan.engineering.azuki.mondex.kazuki.Transfer_Module.mk_Transfer
import com.anaplan.engineering.azuki.mondex.kazuki.World_Module.mk_World
import com.anaplan.engineering.azuki.mondex.kazuki.abstractNullInput
import com.anaplan.engineering.azuki.mondex.adapter.declaration.declaration.WorldOperation
import com.anaplan.engineering.kazuki.core.mk_
import com.anaplan.engineering.kazuki.core.mapping
import com.anaplan.engineering.kazuki.core.toNat

class EnvironmentBuilder {

    private val declarations = LinkedHashMap<String, (ExecutionEnvironment) -> Any>()

    fun <T : Any> declare(name: String, declaration: (ExecutionEnvironment) -> T) {
        if (name in declarations.keys) {
            throw IllegalArgumentException("$name declared more than once")
        }
        declarations[name] = declaration
    }

    fun build(): ExecutionEnvironment {
        val env = ExecutionEnvironment()
        declarations.forEach { (n, fn) -> env.set(n, fn(env)) }
        return env
    }
}

class ExecutionEnvironment {

    val variables = mutableMapOf<String, Any>()

    inline fun <reified T> get(name: String) = variables[name] as T

    inline fun <reified T : Any> set(name: String, value: T) {
        variables[name] = value
    }

    internal fun world(name: String) = get<World>(name)
}

fun buildWorld(
    authPurses: Map<String, Pair<Int, Int>>,
    operations: List<WorldOperation> = emptyList(),
): World {
    var world = mk_World(
        mapping(authPurses.entries) { (name, purse) ->
            mk_(name, mk_Purse(purse.first.toNat(), purse.second.toNat()))
        },
    )
    operations.forEach { operation ->
        world = when (operation) {
            is WorldOperation.Transfer -> {
                val transferDetails = mk_TransferDetails(operation.from, operation.to, operation.value.toNat())
                val input = mk_Transfer(transferDetails)
                world.functions.abstractTransferOkayTD(input, transferDetails)
            }

            WorldOperation.Ignore -> world.functions.abstractIgnore(abstractNullInput)
        }
    }
    return world
}

fun Triple<String, String, Int>.toTransferDetails() =
    mk_TransferDetails(first, second, third.toNat())
