package very.good.purchase.model

import java.math.BigDecimal
import java.util.UUID

data class Pizza(
    var id: UUID = UUID.randomUUID(),
    var amount: BigDecimal = BigDecimal.ZERO,
    var type:  PizzaType = PizzaType.Plain,
)

enum class PizzaType {
    Capricciosa,
    VegetarianSupreme,
    Hawaiian,
    Supreme,
    MeatLovers,
    Plain,
}
