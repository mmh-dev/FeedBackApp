package com.mmh.feedbackapp.repositories

import com.mmh.feedbackapp.db.AppDatabase
import com.mmh.feedbackapp.entities.FeedBack
import javax.inject.Inject

class MyRepository @Inject constructor(
    private val database: AppDatabase
) {

    suspend fun insertFeedback(feedback: FeedBack) {
        database.dao().insertFeedback(feedback)
    }

    suspend fun getAllFeedbacks(): MutableList<FeedBack> {
        return database.dao().getAllFeedbacks()
    }
}