package very.good.purchase

import java.util.Properties
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import very.good.purchase.kafka.PizzaToplogy


private val bootstrapServers: String = "localhost:9092"

fun main() {
    val streamsConfiguration = Properties()
    streamsConfiguration[StreamsConfig.APPLICATION_ID_CONFIG] = "very-good-kafka-streams"
    streamsConfiguration[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
    streamsConfiguration[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] =
        Serdes.String()::class.java.name
    streamsConfiguration[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] =
        Serdes.String()::class.java.name

    val builder = StreamsBuilder()
    PizzaToplogy().countPizzaPipeline(builder)
}
