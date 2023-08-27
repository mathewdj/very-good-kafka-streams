package very.good.purchase.model

import java.math.BigDecimal

data class Pizza(
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
