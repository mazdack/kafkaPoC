package com.syncier.kafkaPoC

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaPoCApplication

fun main(args: Array<String>) {
	runApplication<KafkaPoCApplication>(*args)
}
