package com.example.lab11

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sign_in, container, false)

        val emailEditText: EditText = view.findViewById(R.id.emailEditText)
        val passwordEditText: EditText = view.findViewById(R.id.passwordEditText)
        val signInButton: Button = view.findViewById(R.id.signInButton)
        val signUpButton: Button = view.findViewById(R.id.signUpButton)

        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val receivedEmail = arguments?.getString("email")
            val receivedPassword = arguments?.getString("password")

            if (email == receivedEmail && password == receivedPassword) {
                (activity as `MainActivity`).navigateToHome()
            } else {
                Toast.makeText(requireContext(), "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }

        signUpButton.setOnClickListener {
            (activity as `MainActivity`).navigateToSignUp()
        }

        return view
    }
}
