package com.mmh.feedbackapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.lifecycleScope
import com.mmh.feedbackapp.databinding.FragmentCustomerBinding
import com.mmh.feedbackapp.db.AppDatabase
import com.mmh.feedbackapp.entities.FeedBack
import com.mmh.feedbackapp.utils.CRITIC
import com.mmh.feedbackapp.utils.NEUTRAL
import com.mmh.feedbackapp.utils.PROMOTER
import com.mmh.feedbackapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class CustomerFragment : Fragment() {

    private val binding: FragmentCustomerBinding by lazy {
        FragmentCustomerBinding.inflate(layoutInflater)
    }

    private var score: Int = 0

    @Inject
    lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            scoreGroup.findViewById<RadioButton>(R.id.five).isChecked = true
            scoreGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
                val radio: RadioButton = radioGroup.findViewById(checkedId)
                score = Integer.parseInt(radio.text.toString())
            }

            submitBtn.setOnClickListener {
                lifecycleScope.launch (Dispatchers.IO) {
                    val comment = commentField.text.toString()
                    val feedBack = FeedBack(score, comment)
                    when (score) {
                        in 0..6 -> feedBack.type = CRITIC
                        in 7..8 -> feedBack.type = NEUTRAL
                        else -> feedBack.type = PROMOTER
                    }
                    db.dao().insertFeedback(feedBack)
                    withContext(Dispatchers.Main) {
                        showToast(getString(R.string.feedback_saved_text))
                        binding.apply {
                            commentField.text.clear()
                            scoreGroup.findViewById<RadioButton>(R.id.five).isChecked = true
                        }
                    }
                }
            }
        }
    }
}