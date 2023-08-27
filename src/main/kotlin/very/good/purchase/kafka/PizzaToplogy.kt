package very.good.purchase.kafka

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.utils.Bytes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.state.KeyValueStore
import very.good.purchase.model.Pizza

class PizzaToplogy(
    private val inboundPizzaOrderTopic: String = "inbound-pizza-orders"
) {

    fun buildPipeline(builder: StreamsBuilder) {
        builder.stream<String, String>(inboundPizzaOrderTopic)
            .groupBy { _, value -> value }
            .aggregate(
                { 0L },
                {_, _, count: Long -> count + 1},
                Materialized.`as`<String, Long, KeyValueStore<Bytes, ByteArray>?>(PIZZA_COUNT_TABLE)
                    .withKeySerde(Serdes.String())
                    .withValueSerde(Serdes.Long())
            )
    }

    companion object {
        const val PIZZA_COUNT_TABLE = "pizza-count-table"
    }
}
