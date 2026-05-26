package com.anaplan.engineering.azuki.mondex.kazuki

/**
 * S == [ x: nat | x > 10 ]
 *
 * \delta S
 * =
 * S \land S'
 * =
 * [ x: nat x > 10 ] \land [ x: nat | ]x' > 10 ]
 * =
 * [ x, x': nat | x > 10 \land x' > 10 ]
 */

interface Schema<T> {
    val variable: T

    @Invariant
    fun invariant(): Boolean
}

abstract class AbstractSchema<T> : Schema<T> {
    fun invariant(): Boolean = true
}

interface Delta<T extends Schema> {
    val old: T
    val dash: T
}
