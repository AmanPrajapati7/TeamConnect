package com.example.teamconnect.login

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.teamconnect.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToEmployeeListFragment())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_register.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }

        login_button.setOnClickListener {
            login_user_email_container.error = null
            login_user_password_container.error = null

            val email = login_user_email.text.toString()
            val pass = login_user_password.text.toString()

            if (validateInput(email, pass)) {
                login_progress.visibility = View.VISIBLE

                auth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(requireActivity()) { task ->
                        login_progress.visibility = View.INVISIBLE
                        if (task.isSuccessful) {
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToEmployeeListFragment())
                        } else {
                            val toast = Toast.makeText(
                                requireActivity(),
                                "Authentication Failed. ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            )
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                            toast.show()
                        }
                    }
            }
        }
    }


    private fun validateInput(email: String, pass: String): Boolean {

        if (email.isBlank()) {
            login_user_email_container.error = "Please enter a valid email address"
            return false
        }

        if (pass.isBlank()) {
            login_user_password_container.error = "Please enter password"
            return false
        }

        if (pass.length < 8) {
            login_user_password_container.error = "Password must be 8 character long."
            return false
        }

        return true
    }

}