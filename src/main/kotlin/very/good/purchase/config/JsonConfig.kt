package very.good.purchase.config

import com.fasterxml.jackson.databind.ObjectMapper

object JsonConfig {

    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.findAndRegisterModules()
        return objectMapper
    }
}
