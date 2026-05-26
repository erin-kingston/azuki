package com.anaplan.engineering.azuki.mondex.kazuki

import com.anaplan.engineering.kazuki.core.*

@Module
interface PayDetails : TransferDetails {
    val fromSeqNo: nat
    val toSeqNo: nat

    @Invariant
    fun uniquePayees() = from != to
}
