package com.ch.marcwelz.controller;

import com.ch.marcwelz.api.resource.Feature
import com.ch.marcwelz.featuretogglejava.service.FeatureService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("/rest/features")
class FeatureController(private val service: FeatureService) {

    companion object {
        // const variable have to be const val
        private const val REQUEST_KEY_PARAM = "/{name}"
    }

    @GetMapping(
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllFeatures(): List<Feature> {
        return service.getAllFeatures()
    }

    @GetMapping(
            value = [REQUEST_KEY_PARAM],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun searchFeatures(@NotBlank @PathVariable name: String): Feature? {
        // probably not the best approach to use Feature? but that's fine for now
        return service.searchFeature(name)
    }

    @GetMapping(
            value = ["/value$REQUEST_KEY_PARAM"],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAccessValue(@NotBlank @PathVariable name: String): Boolean {
        return service.getValue(name)
    }

    @PostMapping(
            value = [REQUEST_KEY_PARAM],
            consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createFeature(@NotEmpty @RequestBody @Valid feature: Feature): HttpStatus {
        return service.createFeature(feature)
    }

    @DeleteMapping(
            value = [REQUEST_KEY_PARAM])
    fun deleteFeature(@NotBlank @PathVariable name: String): Boolean {
        return service.deleteFeature(name)
    }
}