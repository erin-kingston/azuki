package com.anaplan.engineering.azuki.mondex.kazuki

import com.anaplan.engineering.kazuki.core.*

typealias Name = String

sealed interface AIN
object aNullIn: AIN
class transfer(val transferDetails: TransferDetails): AIN

sealed interface AOUT
object aNullOut: AOUT

@Module
interface World {
    val authPurses: Mapping<Name, Purse>
    val known: Set<Name>

    // --- World -------
    // | authPurses: Mapping<Name, Purse>,
    // | known: Set<Name>
    // | ----------------
    // | known \neq \{\} \land \\
    // | \dom~authPurse \subset known
    // |------------------
    //
    // TransferDetails = [ from: Name; to: Name; value: nat ]
    // PayDetails = [ TransferDetails | from \neq to ]
    //
    // StartFrom = World \or PayDetails

//    class StartFrom {
//        val world: World
//        val pd: PayDetails
//
//        @Invariant
//        val inv = world.worldContainment() || pd.allInvariant()
//    }

    // OpWorld == [ \Delta World; in? Name | .... ]
    @Invariant
    fun worldContainment() = { known.isNotEmpty() && known subset authPurses.dom }

}
