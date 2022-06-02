package com.mmh.feedbackapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmh.feedbackapp.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment() {

    private val binding: FragmentStartBinding by lazy {
        FragmentStartBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            customerBtn.setOnClickListener {
                parentFragmentManager.beginTransaction().replace(R.id.container_view, CustomerFragment()).addToBackStack(null).commit()
            }

            managerBtn.setOnClickListener {
                parentFragmentManager.beginTransaction().replace(R.id.container_view, ManagerFragment()).addToBackStack(null).commit()
            }
        }

    }

}