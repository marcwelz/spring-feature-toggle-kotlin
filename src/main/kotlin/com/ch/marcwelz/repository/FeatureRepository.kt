package com.ch.marcwelz.repository

import com.ch.marcwelz.api.resource.Feature
import com.ch.marcwelz.api.resource.FeatureFactory

class FeatureRepository {
    fun getAllFeatures(): MutableList<Feature> {
        return FeatureFactory.getAllFeatures()
    }
}