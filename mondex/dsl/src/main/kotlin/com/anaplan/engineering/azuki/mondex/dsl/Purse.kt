package com.anaplan.engineering.azuki.mondex.dsl

data class Purse(
    val balance: Int,
    val lost: Int,
)

fun purse(
    balance: Int,
    lost: Int
): Purse {
    require(balance >= 0 && lost >= 0) { "Purse values must be natural numbers." }
    return Purse(balance, lost)
}

