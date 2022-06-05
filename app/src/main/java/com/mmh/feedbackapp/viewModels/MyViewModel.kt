package com.mmh.feedbackapp.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmh.feedbackapp.entities.FeedBack
import com.mmh.feedbackapp.repositories.MyRepository
import com.mmh.feedbackapp.utils.CRITIC
import com.mmh.feedbackapp.utils.NEUTRAL
import com.mmh.feedbackapp.utils.round
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {

    private val _feedBackList = MutableLiveData<List<FeedBack>>()
    val feedBackList: LiveData<List<FeedBack>> = _feedBackList
    private val _numbers = MutableLiveData<List<String>>()
    val numbers: LiveData<List<String>> = _numbers

    fun getFeedbacks() {
        viewModelScope.launch {
            val list = repository.getAllFeedbacks()
            _feedBackList.value = list
            var critics = 0
            var neutrals = 0
            var promoters = 0
            var totalScore = 0
            list.forEach {
                totalScore += it.score
                when (it.type) {
                    CRITIC -> critics++
                    NEUTRAL -> neutrals++
                    else -> promoters++
                }
            }
            if (list.isNotEmpty()) {
                val numbersList = mutableListOf<String>()
                numbersList.add(critics.toString())
                numbersList.add(neutrals.toString())
                numbersList.add(promoters.toString())
                if (list.isNotEmpty()) {
                    val avScore: Double = totalScore.toDouble() / list.size
                    numbersList.add(avScore.round().toString())
                }
                _numbers.value = numbersList
            }
        }
    }

    fun insertFeedback(feedBack: FeedBack) {
        viewModelScope.launch {
            repository.insertFeedback(feedBack)
        }
    }
}