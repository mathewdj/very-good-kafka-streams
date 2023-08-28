package very.good.purchase

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID
import very.good.purchase.model.Pizza
import very.good.purchase.model.PizzaType

object TestHelpers {

    private val localDate = LocalDate.of(2023, 8, 29)

    fun givenPizza(
        id: UUID = UUID.randomUUID(),
        amount: BigDecimal = BigDecimal("9.99"),
        type: PizzaType = PizzaType.Hawaiian,
        eventDate: LocalDate = localDate,
    ) = Pizza(id, amount, type, eventDate)
}
