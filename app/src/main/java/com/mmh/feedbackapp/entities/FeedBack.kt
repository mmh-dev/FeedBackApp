package com.mmh.feedbackapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FeedBack(
    @PrimaryKey(autoGenerate = true) val id: Int,
    var score: Int,
    var comment: String,
) {
    constructor(score: Int, comment: String) : this(0, score, comment)
}