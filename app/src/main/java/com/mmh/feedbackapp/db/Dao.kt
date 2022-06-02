package com.mmh.feedbackapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mmh.feedbackapp.entities.FeedBack

@Dao
interface Dao {

    @Insert
    suspend fun insertFeedback(feedBack: FeedBack)

    @Query("Select * from feedback")
    suspend fun getAllFeedbacks(): MutableList<FeedBack>

}