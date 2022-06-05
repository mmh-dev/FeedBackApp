package com.mmh.feedbackapp.utils

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Double.round(decimals: Int = 2): Double = "%.${decimals}f".format(this).toDouble()