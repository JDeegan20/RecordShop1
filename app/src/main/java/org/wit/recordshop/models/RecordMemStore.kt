package org.wit.recordshop.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class RecordMemStore : RecordStore {

    val records = ArrayList<RecordModel>()

    override fun findAll(): List<RecordModel> {
        return records
    }

    override fun create(record: RecordModel) {
        record.id = getId()
        records.add(record)
        logAll()
    }

    override fun update(record: RecordModel) {
        var foundRecord: RecordModel? = records.find { p -> p.id == record.id }
        if (foundRecord != null) {
            foundRecord.title = record.title
            foundRecord.description = record.description
            foundRecord.genre = record.genre
            foundRecord.image = record.image
            foundRecord.lat = record.lat
            foundRecord.lng = record.lng
            foundRecord.zoom = record.zoom
            logAll()
        }
    }

    override fun delete(record: RecordModel) {
        records.remove(record)
    }


    private fun logAll() {
        records.forEach { i("$it") }
    }

    override fun findById(id:Long) : RecordModel? {
        val foundRecord: RecordModel? = records.find { it.id == id }
        return foundRecord
    }

}
