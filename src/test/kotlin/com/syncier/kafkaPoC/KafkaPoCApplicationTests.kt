package com.syncier.kafkaPoC

import org.awaitility.Awaitility.await
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.context.EmbeddedKafka
import java.util.concurrent.TimeUnit

@SpringBootTest
@EmbeddedKafka(topics = [TOPIC], bootstrapServersProperty="spring.kafka.bootstrap-servers")
class KafkaPoCApplicationTests {
	@Autowired
	private lateinit var kafkaTemplate: KafkaTemplate<Int, GoodDxo>
	@Autowired
	private lateinit var myRepository: MyRepository

	@BeforeEach
	fun setUp() {
		myRepository.clear()
	}

	@Test
	fun contextLoads() {
		val goodDxo = GoodDxo(1, "test")
		kafkaTemplate.send(TOPIC, goodDxo)

		await().atMost(1, TimeUnit.SECONDS).untilAsserted {
			assertNotNull(myRepository.get(goodDxo.id))
		}

	}

	@Test
	fun `impossible to deserialize message does not block listener from processing next messages`() {

	}

}
