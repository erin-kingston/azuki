package com.anaplan.engineering.azuki.mondex.adapter.declaration.declaration

import com.anaplan.engineering.azuki.declaration.Declaration
import com.anaplan.engineering.azuki.mondex.adapter.api.Purse

data class PurseDeclaration(
    override val name: String,
    val purse: Purse,
    override val standalone: Boolean,
) : Declaration
