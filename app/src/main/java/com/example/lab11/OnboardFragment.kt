package com.example.lab11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class OnboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_onboard, container, false)

        val nextButton: Button = view.findViewById(R.id.nextButton)
        nextButton.setOnClickListener {
            (activity as `MainActivity`).navigateToSignIn()
        }

        return view
    }
}