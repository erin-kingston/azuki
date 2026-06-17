package com.anaplan.engineering.azuki.mondex.eacs

import com.anaplan.engineering.azuki.core.runner.Eac
import com.anaplan.engineering.azuki.core.system.BEH
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexBehaviours
import com.anaplan.engineering.azuki.mondex.adapter.api.MondexFunctionalElements
import com.anaplan.engineering.azuki.mondex.dsl.MondexScenario

@BEH(MondexBehaviours.CreateWorld, MondexFunctionalElements.World, """
    Create a world with authentic purses
""")
class BEH1 : MondexScenario() {

    @Eac("Transfer between authentic purses succeeds")
    fun transferOkay() {
        given {
            thereIsAWorld {
                personWithPurse("person1", (3UL to 0UL))
                personWithPurse("person2", (2UL to 1UL))
            }
        }
        whenever {
            absTransfer(Triple("person1", "person2", 3UL))
        }
        then {
            purseExists(purseName = "person1", 0UL, 0UL)
            purseExists(purseName = "person2", 5UL, 1UL)
            worldExists(mapOf("person1" to (0UL to 0UL), "person2" to (5UL to 1UL)))
        }
    }
}
