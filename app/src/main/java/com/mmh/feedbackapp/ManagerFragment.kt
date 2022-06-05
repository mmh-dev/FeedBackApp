package com.mmh.feedbackapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmh.feedbackapp.adapters.FeedBackAdapter
import com.mmh.feedbackapp.databinding.FragmentManagerBinding
import com.mmh.feedbackapp.entities.FeedBack
import com.mmh.feedbackapp.utils.ALL
import com.mmh.feedbackapp.utils.CRITIC
import com.mmh.feedbackapp.utils.NEUTRAL
import com.mmh.feedbackapp.utils.PROMOTER
import com.mmh.feedbackapp.viewModels.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class ManagerFragment : Fragment() {

    private val binding: FragmentManagerBinding by lazy {
        FragmentManagerBinding.inflate(layoutInflater)
    }
    private var feedBackAdapter: FeedBackAdapter? = null
    private var feedbackList = listOf<FeedBack>()
    private val viewModel: MyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedBackAdapter = FeedBackAdapter(requireContext())
        binding.commentsRv.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = feedBackAdapter
        }

        viewModel.getFeedbacks()
        binding.toggleGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            val checkedType = when (checkedId) {
                binding.criticsBtn.id -> CRITIC
                binding.neutralsBtn.id -> NEUTRAL
                binding.promoterBtn.id -> PROMOTER
                else -> ALL
            }
            Log.i("tag", checkedType)
            if (checkedType != ALL) {
                val filteredList = feedbackList.filter { it.type == checkedType }
                feedBackAdapter?.submitList(filteredList)
            } else {
                feedBackAdapter?.submitList(feedbackList)
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.feedBackList.observe(viewLifecycleOwner) { list ->
                feedbackList = list
                feedBackAdapter?.submitList(feedbackList)
            }
            updateNumbers()
        }
    }

    private fun updateNumbers() {
        binding.apply {
            viewModel.numbers.observe(viewLifecycleOwner) { numbers ->
                criticsNumber.text = numbers.first()
                neutralsNumber.text = numbers[1]
                promotersNumber.text = numbers[2]
                averageScore.text = numbers.last()
            }
        }
    }
}