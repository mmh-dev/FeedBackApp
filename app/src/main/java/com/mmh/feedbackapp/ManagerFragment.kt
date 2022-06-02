package com.mmh.feedbackapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.mmh.feedbackapp.databinding.FragmentCustomerBinding
import com.mmh.feedbackapp.databinding.FragmentManagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManagerFragment : Fragment() {

    private val binding: FragmentManagerBinding by lazy {
        FragmentManagerBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}