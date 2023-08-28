package very.good.purchase

import java.math.BigDecimal
import java.util.UUID
import very.good.purchase.model.Pizza
import very.good.purchase.model.PizzaType

object TestHelpers {
    fun givenPizza(
        id: UUID = UUID.randomUUID(),
        amount: BigDecimal = BigDecimal("9.99"),
        type: PizzaType = PizzaType.Hawaiian,
    ) = Pizza(id, amount, type)
}
