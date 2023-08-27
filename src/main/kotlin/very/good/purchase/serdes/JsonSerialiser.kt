package very.good.purchase.serdes

import org.apache.kafka.common.serialization.Serializer
import very.good.purchase.config.JsonConfig

class JsonSerialiser<T> : Serializer<T> {
    override fun serialize(topic: String, data: T): ByteArray {
        return JsonConfig.objectMapper().writeValueAsString(data).encodeToByteArray()
    }
}
