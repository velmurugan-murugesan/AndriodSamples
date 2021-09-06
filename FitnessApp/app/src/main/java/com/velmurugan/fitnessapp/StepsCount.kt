package com.velmurugan.fitnessapp

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_NAME)
data class StepsCount(
    @PrimaryKey(autoGenerate = true)
    @NonNull val id: Long? = null,
    val steps: Int? = null,
    val lastReboot: String? = null,
    val date: String? = null,
)