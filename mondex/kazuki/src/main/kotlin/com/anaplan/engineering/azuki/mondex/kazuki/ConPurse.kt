package com.anaplan.engineering.azuki.mondex.kazuki

import com.anaplan.engineering.kazuki.core.*

enum class STATUS { eaFrom, eaTo, epr, epv, epa }


@Module
interface ConPurse {
    var balance: nat
    var exLog: SeT<PaymentDetails>
    var name: Name
    var nextSeqNo: nat
    var pdAuth: PayDetails
    var status: STATUS

    @Invariant
    fun knownLogs() =
        forall(pd in exLog) { name in mk_Set(pd.from, pd.to) }

    @Invariant
    fun statusEpr() =
        status == STATUS.epr
        implies
        name == pdAuth.from && pdAuth.value <= balance && pdAuth.fromSeqNo < nextSeqNo
    //.... status = epv... statis = epa ..
}

interface ConcretePurseIncrease : ConPurse {
    //??? getnextSeqNo() = { throw INVALID() } ??
}

enum class MESSAGE { BOTTOM, INVALID, OKAY }

class IncreasePurseOkay : Delta<ConPurse> {
    val m?: MESSAGE
    val m!: MESSAGE
        // ConPurse.invariants checks
    // \Xi ConPurseIncrease invariants too
    // \Xi ConPurseIncrease = [ \Delta ConPurseIncrease | \Theta ConPurseIncrease' = Theta ConPurseIncrease ]
    // =
    // [ old: ConPurse, dash: ConPurse | old.invariant() and dash.invariant() and old = dash (except for nextSeqNo) ]
    @Invariant
    fun nextSeqNoIncrease() = dash.nextSeqNo >= old.nextSeqNo && m! = BOTTOM
}
