package org.wit.recordshop.models

import timber.log.Timber.i

class RecordMemStore : RecordStore {

    val records = ArrayList<RecordModel>()

    override fun findAll(): List<RecordModel> {
        return records
    }

    override fun create(record: RecordModel) {
        records.add(record)
        logAll()
    }

    fun logAll() {
        records.forEach{ i("${it}") }
    }
}
