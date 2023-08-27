package very.good.purchase.model

import java.math.BigDecimal

data class Pizza(
    val amount: BigDecimal,
    val type:  PizzaType,
)

enum class PizzaType {
    Capricciosa,
    VegetarianSupreme,
    Hawaiian,
    Supreme,
    MeatLovers,
}
