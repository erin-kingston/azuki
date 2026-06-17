package com.anaplan.engineering.azuki.mondex.eacs

import com.anaplan.engineering.azuki.core.runner.Eac
import com.anaplan.engineering.azuki.core.system.BEH
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexBehaviours
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexFunctionalElements
import com.anaplan.engineering.azuki.mondex.dsl.MondexScenario
import com.anaplan.engineering.azuki.mondex.dsl.purse
import com.anaplan.engineering.azuki.mondex.dsl.transferDetails
import com.anaplan.engineering.azuki.mondex.person1
import com.anaplan.engineering.azuki.mondex.person2

@BEH(MondexBehaviours.CreateWorld, MondexFunctionalElements.World, """
    Create a world with authentic purses
""")
class BEH1 : MondexScenario() {

    @Eac("Transfer between authentic purses succeeds")
    fun transferOkay() {
        given {
            thereIsAWorld {
                personWithPurse(person1, purse(3, 0))
                personWithPurse(person2, purse(2, 1))
            }
        }
        whenever {
            thereIsATransfer(transferDetails(person1, person2, 3))
        }
        then {
            purseExists(person1, purse(0, 0))
            purseExists(person2, purse(5, 1))
            worldExists {
                personWithPurse(person1, purse(0, 0))
                personWithPurse(person2, purse(5, 1))
            }
        }
    }
}
