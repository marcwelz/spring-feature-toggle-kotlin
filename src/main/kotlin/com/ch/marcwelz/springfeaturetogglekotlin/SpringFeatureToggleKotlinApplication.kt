package com.ch.marcwelz.springfeaturetogglekotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringFeatureToggleKotlinApplication

fun main(args: Array<String>) {
    runApplication<SpringFeatureToggleKotlinApplication>(*args)
}
