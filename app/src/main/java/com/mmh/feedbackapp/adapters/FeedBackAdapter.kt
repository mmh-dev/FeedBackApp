package com.mmh.feedbackapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mmh.feedbackapp.R
import com.mmh.feedbackapp.databinding.FeedbackItemBinding
import com.mmh.feedbackapp.entities.FeedBack
import com.mmh.feedbackapp.utils.CRITIC
import com.mmh.feedbackapp.utils.PROMOTER
import javax.inject.Inject

class FeedBackAdapter @Inject constructor(private val context: Context) :
    ListAdapter<FeedBack, FeedBackAdapter.FeedBackViewHolder>(FeedbackDiffUtils()) {

    inner class FeedBackViewHolder(private val binding: FeedbackItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(feedBack: FeedBack) {
            with(binding) {
                comment.text = feedBack.comment
                when (feedBack.type) {
                    CRITIC -> comment.setTextColor(context.resources.getColor(R.color.red))
                    PROMOTER -> comment.setTextColor(context.resources.getColor(R.color.green))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedBackViewHolder {
        val binding = FeedbackItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedBackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedBackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FeedbackDiffUtils : DiffUtil.ItemCallback<FeedBack>() {
        override fun areItemsTheSame(oldItem: FeedBack, newItem: FeedBack): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FeedBack, newItem: FeedBack): Boolean {
            return oldItem == newItem
        }
    }
}
