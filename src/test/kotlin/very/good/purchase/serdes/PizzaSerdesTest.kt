package very.good.purchase.serdes

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import very.good.purchase.TestHelpers.givenPizza

class PizzaSerdesTest {
    @Test
    fun `should serialise pizza`() {
        val pizza = givenPizza()
        val bytes = PizzaSerdes.serdes().serializer().serialize("any", pizza)
        val deserialisedPizza = PizzaSerdes.serdes().deserializer().deserialize("any", bytes)

        assertThat(pizza).isEqualTo(deserialisedPizza)
    }
}
