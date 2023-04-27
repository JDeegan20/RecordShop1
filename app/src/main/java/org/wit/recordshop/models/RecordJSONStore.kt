package org.wit.recordshop.models

import android.content.Context
import android.net.Uri
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.wit.recordshop.helpers.*
import org.wit.recordshop.helpers.exists
import org.wit.recordshop.helpers.read
import org.wit.recordshop.helpers.write
import timber.log.Timber
import java.lang.reflect.Type
import java.util.*

const val JSON_FILE = "records.json"
val gsonBuilder: Gson = GsonBuilder().setPrettyPrinting()
    .registerTypeAdapter(Uri::class.java, UriParser())
    .create()
val listType: Type = object : TypeToken<ArrayList<RecordModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class RecordJSONStore(private val context: Context) : RecordStore {

    var records = mutableListOf<RecordModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<RecordModel> {
        logAll()
        return records
    }

    override fun create(record: RecordModel) {
        record.id = generateRandomId()
        records.add(record)
        serialize()
    }


    override fun update(record: RecordModel) {
        val recordsList = findAll() as ArrayList<RecordModel>
        var foundRecord: RecordModel? = recordsList.find { p -> p.id == record.id }
        if (foundRecord != null) {
            foundRecord.title = record.title
            foundRecord.description = record.description
            foundRecord.genre = record.genre
            foundRecord.image = record.image
            foundRecord.lat = record.lat
            foundRecord.lng = record.lng
            foundRecord.zoom = record.zoom
        }
        serialize()
    }


    private fun serialize() {
        val jsonString = gsonBuilder.toJson(records, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        records = gsonBuilder.fromJson(jsonString, listType)
    }

    private fun logAll() {
        records.forEach { Timber.i("$it") }
    }
}

class UriParser : JsonDeserializer<Uri>,JsonSerializer<Uri> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Uri {
        return Uri.parse(json?.asString)
    }

    override fun serialize(
        src: Uri?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }
}

