package com.ch.marcwelz.api.resource

import java.time.LocalDateTime

/*
    Lombok not needed, already built in Kotlin
    ? means that it can be null
 */

data class Feature(
    val name: String,
    val comment: String?,
    val value: Boolean,
    val validFrom: LocalDateTime?,
    val validTo: LocalDateTime?
)