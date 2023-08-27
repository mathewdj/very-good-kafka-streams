package very.good.purchase.serdes

import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.serialization.Serdes.WrapperSerde
import very.good.purchase.model.Pizza

class PizzaSerdes : WrapperSerde<Pizza>(
    JsonSerialiser<Pizza>(),
    JsonDeserialiser(Pizza::class.java),
) {
    companion object {
        fun serdes(): Serde<Pizza> {
            return Serdes.serdeFrom(
                JsonSerialiser<Pizza>(),
                JsonDeserialiser(Pizza::class.java),
            )
        }
    }
}
