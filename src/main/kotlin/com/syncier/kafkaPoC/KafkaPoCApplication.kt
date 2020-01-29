package com.syncier.kafkaPoC

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

const val TOPIC = "topic"

@SpringBootApplication
class KafkaPoCApplication

@Service
class MyService(private val myRepository: MyRepository) {
	@KafkaListener(topics = [TOPIC])
	fun listen(goodDxo: GoodDxo) {
		println("got a good dxo $goodDxo")
		myRepository.add(goodDxo)
	}
}

@Repository
class MyRepository {
	private val db = HashMap<Int, GoodDxo>()

	fun add(goodDxo: GoodDxo) = db.put(goodDxo.id, goodDxo)
	fun get(id: Int) = db[id]
	fun clear() = db.clear()

}

fun main(args: Array<String>) {
	runApplication<KafkaPoCApplication>(*args)
}
