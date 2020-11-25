package com.kotlin.cccandroidtest.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Estimate(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "company") val company: String?,
    @ColumnInfo(name = "address") val address: String?,
    @ColumnInfo(name = "number") val number: Int?,
    @ColumnInfo(name = "revision_number") val revisionNumber: Int?,
    @ColumnInfo(name = "created_date") val createdDate: String?,
    @ColumnInfo(name = "created_by") val createdBy: String?,
    @ColumnInfo(name = "requested_by") val requestedBy: String?,
    @ColumnInfo(name = "contact") val contact: String?
)

@Entity
data class Person(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "first_name") val firstName: String?,
    @ColumnInfo(name = "last_name") val lastName: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "phone_number") val phoneNumber: String?
)

data class EstimateWithPerson(
    val id: String?,
    val company: String?,
    val address: String?,
    val number: Int?,
    val revisionNumber: Int?,
    val createdDate: String?,
    val personConnWithCreatedBy: Person?,
    val personConnWithRequestedBy: Person?,
    val personConnWithContact: Person?
)