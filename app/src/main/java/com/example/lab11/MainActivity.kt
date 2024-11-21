package com.example.lab11

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class `MainActivity` : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigateToOnboard()
        }
    }

    fun navigateToOnboard() {
        replaceFragment(OnboardFragment())
    }

    fun navigateToSignIn(bundle: Bundle? = null) {
        val fragment = SignInFragment()
        fragment.arguments = bundle
        replaceFragment(fragment)
    }

    fun navigateToSignUp() {
        replaceFragment(SignUpFragment())
    }

    fun navigateToHome() {
        replaceFragment(`HomeFragment`())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
