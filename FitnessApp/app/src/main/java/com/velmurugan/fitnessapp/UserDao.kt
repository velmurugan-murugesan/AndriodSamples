package com.velmurugan.fitnessapp

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToRoomDatabase(stepsCount: StepsCount) : Long

    @Query("SELECT * FROM step_count ORDER BY id DESC")
    fun getUserDetails() : List<StepsCount>

}