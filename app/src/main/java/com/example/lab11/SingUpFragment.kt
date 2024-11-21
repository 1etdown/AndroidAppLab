package com.example.lab11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_signup, container, false)

        val nameEditText: EditText = view.findViewById(R.id.nameEditText)
        val emailEditText: EditText = view.findViewById(R.id.emailEditText)
        val passwordEditText: EditText = view.findViewById(R.id.passwordEditText)
        val signupButton: Button = view.findViewById(R.id.signupButton)

        signupButton.setOnClickListener {
            val bundle = Bundle().apply {
                putString("name", nameEditText.text.toString())
                putString("email", emailEditText.text.toString())
                putString("password", passwordEditText.text.toString())
            }
            (activity as `MainActivity`).navigateToSignIn(bundle)
        }

        return view
    }
}
