package very.good.purchase.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer
import java.math.BigDecimal

object JsonConfig {

    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.findAndRegisterModules()

        val module = SimpleModule()
        module.addSerializer(BigDecimal::class.java, ToStringSerializer());
        objectMapper.registerModule(module)

        return objectMapper
    }
}
