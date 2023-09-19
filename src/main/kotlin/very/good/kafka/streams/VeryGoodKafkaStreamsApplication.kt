package very.good.kafka.streams

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VeryGoodKafkaStreamsApplication

fun main(args: Array<String>) {
	runApplication<VeryGoodKafkaStreamsApplication>(*args)
}
