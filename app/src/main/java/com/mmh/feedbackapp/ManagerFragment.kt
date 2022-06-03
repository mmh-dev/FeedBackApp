package com.mmh.feedbackapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mmh.feedbackapp.adapters.FeedBackAdapter
import com.mmh.feedbackapp.databinding.FragmentManagerBinding
import com.mmh.feedbackapp.db.AppDatabase
import com.mmh.feedbackapp.entities.FeedBack
import com.mmh.feedbackapp.utils.CRITIC
import com.mmh.feedbackapp.utils.NEUTRAL
import com.mmh.feedbackapp.utils.PROMOTER
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ManagerFragment : Fragment() {

    private val binding: FragmentManagerBinding by lazy {
        FragmentManagerBinding.inflate(layoutInflater)
    }
    private var feedBackAdapter: FeedBackAdapter? = null
    private var checkedType = CRITIC
    private var feedbackList = mutableListOf<FeedBack>()

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

        feedBackAdapter = FeedBackAdapter(requireContext())
        binding.commentsRv.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
            adapter = feedBackAdapter
        }

        binding.toggleGroup.addOnButtonCheckedListener { group, checkedId, isChecked ->
            checkedType = when (checkedId) {
                binding.criticsBtn.id -> CRITIC
                binding.neutralsBtn.id -> NEUTRAL
                binding.promoterBtn.id -> PROMOTER
                else -> ""
            }
            Log.i("tag", checkedType)
            feedbackList.filter { it.type == checkedType }
            feedBackAdapter?.submitList(feedbackList)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            feedbackList = db.dao().getAllFeedbacks()
        }
    }
}