package very.good.purchase.kafka

import java.util.Properties
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.serialization.StringSerializer
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG
import org.apache.kafka.streams.StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG
import org.apache.kafka.streams.TestInputTopic
import org.apache.kafka.streams.TopologyTestDriver
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import very.good.purchase.TestHelpers.givenPizza
import very.good.purchase.model.Pizza
import very.good.purchase.model.PizzaType
import very.good.purchase.serdes.PizzaSerdes

class PizzaToplogyTest {
    private lateinit var pizzaToplogy: PizzaToplogy
    private lateinit var topologyTestDriver: TopologyTestDriver
    private lateinit var inputTopic: TestInputTopic<String, Pizza>
    private val streamsBuilder = StreamsBuilder()

    @BeforeEach
    fun setup() {
        pizzaToplogy = PizzaToplogy(INBOUND_PIZZA_ORDER_TOPIC)

        pizzaToplogy.buildPipeline(streamsBuilder)
        val topology = streamsBuilder.build()

        val config = Properties()
        config[DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String()::class.java.name
        config[DEFAULT_VALUE_SERDE_CLASS_CONFIG] = PizzaSerdes::class.java.name

        topologyTestDriver = TopologyTestDriver(topology, config)
        inputTopic = topologyTestDriver.createInputTopic(
            INBOUND_PIZZA_ORDER_TOPIC,
            StringSerializer(),
            PizzaSerdes.serdes().serializer()
        )
    }

    @Test
    fun `given stream of pizzas, then they are counted`() {
        inputTopic.pipeInput("key", givenPizza(type = PizzaType.Hawaiian))
        inputTopic.pipeInput("key", givenPizza(type = PizzaType.Hawaiian))
        inputTopic.pipeInput("key", givenPizza(type = PizzaType.Capricciosa))

        val store = topologyTestDriver.getKeyValueStore<String, Long>(PizzaToplogy.PIZZA_COUNT_TABLE)
        assertThat(store[PizzaType.Hawaiian.name]).isEqualTo(2L)
        assertThat(store[PizzaType.Capricciosa.name]).isEqualTo(1L)
    }

    companion object {
        const val INBOUND_PIZZA_ORDER_TOPIC = "pizza-orders"
    }
}
