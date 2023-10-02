package com.ch.marcwelz.api.resource

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// object = singleton
object FeatureFactory {
    fun getAllFeatures(): MutableList<Feature> {
        // it has to be mutable List so you can use methods like .add, .remove, etc
        return mutableListOf(
                Feature("feature-1", "to be implemented", false, LocalDateTime.now(), null),
                Feature("feature-2", "done", false, null, null),
                Feature("feature-3", null, false, null, null),
                Feature("feature-4", "finish after release xxx", false, null, null),
                Feature(
                        "feature-5", "done", true,
                        LocalDateTime.parse("18.08.2023 12:30:30", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")),
                        LocalDateTime.parse("20.08.2023 17:43:22", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
                ),
                Feature(
                        "feature-6", "in progress", false, LocalDateTime.now(),
                        LocalDateTime.parse("30.10.2023 10:00:00", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
                )
        )
    }
}