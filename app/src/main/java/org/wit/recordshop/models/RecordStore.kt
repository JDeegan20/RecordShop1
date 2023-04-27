package org.wit.recordshop.models

interface RecordStore {
    fun findAll(): List<RecordModel>
    fun create(record: RecordModel)
    fun update(record: RecordModel)

    fun delete(record: RecordModel)


}