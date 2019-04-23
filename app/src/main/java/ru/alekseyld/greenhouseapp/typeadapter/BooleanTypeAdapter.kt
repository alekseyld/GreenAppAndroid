package ru.alekseyld.greenhouseapp.typeadapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class BooleanTypeAdapter : JsonDeserializer<Boolean> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Boolean {
        val data = json?.asString

        data?.let {
            if(data.contains("1")) {
                return true
            }
        }

        return false
    }
}