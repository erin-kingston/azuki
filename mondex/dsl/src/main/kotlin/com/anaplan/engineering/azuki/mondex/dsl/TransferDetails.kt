package com.anaplan.engineering.azuki.mondex.dsl

data class TransferDetails(
    val fromPurse: String,
    val toPurse: String,
    val value: Int
)

fun transferDetails(
    fromPurse: String,
    toPurse: String,
    value: Int
): TransferDetails {
    require(value >= 0) { "Transfer value must be a natural number." }
    return TransferDetails(fromPurse, toPurse, value)
}
