package com.ch.marcwelz.featuretogglejava.service

import com.ch.marcwelz.api.resource.Feature
import com.ch.marcwelz.repository.FeatureRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FeatureService(private val repository: FeatureRepository) {

    // implement slf4j logger, can probably be done better :)
    private val logger: org.slf4j.Logger? = LoggerFactory.getLogger(FeatureService::class.java)
    fun getAllFeatures(): MutableList<Feature> {
        return getFeatures()
    }

    // Exception not implemented.
    // "?" added so null can be returned
    fun searchFeature(name: String): Feature? {
        val feature:Feature? = getFeatures().firstOrNull { it.name == name }
        if (feature == null) logger?.error("No feature with key '$name' could be found")
        return feature
    }

    fun createFeature(feature: Feature): HttpStatus {
        val featureList:MutableList<Feature> = getAllFeatures()
        if (searchFeature(feature.name) != null) {
            logger?.error("Feature '${feature.name}' already exists!")
        }
        featureList.add(feature)
        return HttpStatus.CREATED
    }

    fun getValue(name: String): Boolean {
        // check if feature is present.
        // if feature cannot be found, return false.
        // Exception not implemented.
        val feature:Feature = searchFeature(name) ?: return false
        return feature.value && checkTime(feature)
    }

    fun deleteFeature(name: String): Boolean {
        val featureList:MutableList<Feature> = getAllFeatures()
        // weird way to use removeIf compared to java, but it works I guess :)
        if (!featureList.removeIf { it.name == name }) {
            logger?.error("No feature with key '$name' could be found")
            return false
        }
        return true
    }

    private fun getFeatures(): MutableList<Feature> {
        val featureList:MutableList<Feature> = repository.getAllFeatures()
        if (featureList.isEmpty()) {
            logger?.info("Feature list is empty")

            // return empty list
            return mutableListOf()
        }
        return featureList
    }

    private fun checkTime(feature: Feature): Boolean {
        val currentTime:LocalDateTime = LocalDateTime.now()
        // validTo and ValidFrom can be null
        if (feature.validFrom == null && feature.validTo == null) return true
        return currentTime.isAfter(feature.validFrom) && currentTime.isBefore(feature.validTo)
    }
}