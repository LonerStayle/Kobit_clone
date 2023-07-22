package kr.loner.data

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

object TickerDtoAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: Set<Annotation>, moshi: Moshi): JsonAdapter<*>? {
        if (Types.getRawType(type) != TickerAllDto::class.java) {
            return null
        }
        return TickerDtoAdapter(moshi).nullSafe()
    }
}

class TickerDtoAdapter(moshi: Moshi) : JsonAdapter<TickerDto>() {
    private val delegateAdapter = moshi.adapter(TickerDto::class.java)

    override fun fromJson(reader: JsonReader): TickerDto? {
        val jsonValue = reader.readJsonValue()
        if (jsonValue is Map<*, *>) {
            val currencyPair = jsonValue.keys.firstOrNull()?.toString() ?: return null
            val modifiedJson = jsonValue.toMutableMap().apply {
                this[currencyPair] =
                    (this[currencyPair] as? Map<*, *>)?.plus("currencyPair" to currencyPair)
            }
            return delegateAdapter.fromJsonValue(modifiedJson)
        }
        return null
    }

    override fun toJson(writer: JsonWriter, value: TickerDto?) {
        delegateAdapter.toJson(writer, value)
    }
}