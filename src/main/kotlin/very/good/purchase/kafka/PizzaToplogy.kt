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

    fun countPizzaPipeline(builder: StreamsBuilder) {
        builder.stream<String, Pizza>(inboundPizzaOrderTopic)
            .groupBy { _, value -> value.type.name }
            .aggregate(
                { 0L },
                {_, _: Pizza, count: Long -> count + 1},
                Materialized.`as`<String, Long, KeyValueStore<Bytes, ByteArray>?>(PIZZA_COUNT_TABLE)
                    .withKeySerde(Serdes.String())
                    .withValueSerde(Serdes.Long())
            )
    }

    companion object {
        const val PIZZA_COUNT_TABLE = "pizza-count-table"
    }
}
