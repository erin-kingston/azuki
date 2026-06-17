package com.anaplan.engineering.azuki.mondex.dsl

data class Purse(
    val balance: Int,
    val lost: Int,
)

fun purse(
    balance: Int,
    lost: Int
) = Purse(balance, lost)
