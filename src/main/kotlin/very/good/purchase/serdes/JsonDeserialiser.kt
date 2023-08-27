package very.good.purchase.serdes

import java.nio.charset.StandardCharsets
import org.apache.kafka.common.serialization.Deserializer
import very.good.purchase.config.JsonConfig

class JsonDeserialiser<T>(
    private val clazz: Class<T>
) : Deserializer<T> {

    override fun deserialize(topic: String, data: ByteArray): T {
        return JsonConfig.objectMapper().readValue(String(data, StandardCharsets.UTF_8), clazz)
    }

}
