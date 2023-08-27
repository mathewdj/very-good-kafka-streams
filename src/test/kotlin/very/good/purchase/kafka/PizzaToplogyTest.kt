package very.good.purchase.kafka

import java.util.Properties
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG
import org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG
import org.apache.kafka.streams.TopologyTestDriver
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import very.good.purchase.TestHelpers.givenPizza
import very.good.purchase.model.PizzaType
import very.good.purchase.serdes.PizzaSerdes

class PizzaToplogyTest {
    private lateinit var pizzaToplogy: PizzaToplogy

    @BeforeEach
    fun setup() {
        pizzaToplogy = PizzaToplogy(INBOUND_PIZZA_ORDER_TOPIC)
    }

    @Test
    fun `given stream of pizzas, then they are counted`() {
        val streamsBuilder = StreamsBuilder()
        pizzaToplogy.buildPipeline(streamsBuilder)
        val topology = streamsBuilder.build()

        val config = Properties()
        config[DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String()::class.java.name
        config[DEFAULT_VALUE_SERDE_CLASS_CONFIG] = Serdes.String()::class.java.name

        val topologyTestDriver = TopologyTestDriver(topology, config)
        val inputTopic = topologyTestDriver.createInputTopic(
            INBOUND_PIZZA_ORDER_TOPIC,
            StringSerializer(),
            Serdes.String().serializer()
        )

        inputTopic.pipeInput("key", PizzaType.Hawaiian.name)

        val store = topologyTestDriver.getKeyValueStore<String, Long>(PizzaToplogy.PIZZA_COUNT_TABLE)
        assertThat(store[PizzaType.Hawaiian.name]).isEqualTo(1L)
    }

    companion object {
        const val INBOUND_PIZZA_ORDER_TOPIC = "pizza-orders"
    }
}
