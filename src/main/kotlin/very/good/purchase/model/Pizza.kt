package very.good.purchase.model

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

data class Pizza(
    var id: UUID = UUID.randomUUID(),
    var amount: BigDecimal = BigDecimal.ZERO,
    var type:  PizzaType = PizzaType.Plain,
    var eventDate: LocalDate = LocalDate.now()
)

enum class PizzaType {
    Capricciosa,
    VegetarianSupreme,
    Hawaiian,
    Supreme,
    MeatLovers,
    Plain,
}
