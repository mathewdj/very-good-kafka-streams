package very.good.purchase

import java.math.BigDecimal
import very.good.purchase.model.Pizza
import very.good.purchase.model.PizzaType

object TestHelpers {
    fun givenPizza(
        amount: BigDecimal = BigDecimal("9.99"),
        type: PizzaType = PizzaType.Hawaiian,
    ) = Pizza(amount, type)
}
