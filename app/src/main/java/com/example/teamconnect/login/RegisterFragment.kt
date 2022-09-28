package com.example.teamconnect.login

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import androidx.navigation.fragment.findNavController
import com.example.teamconnect.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_login.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

        register_button.setOnClickListener {
            register_user_password_container.error = null
            register_user_email_container.error = null

            val email = register_user_email.text.toString()
            val pass = register_user_password.text.toString()

            if (validateInput(email, pass)) {
                register_progress.visibility = View.VISIBLE

                auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(requireActivity()) { task ->
                        register_progress.visibility = View.INVISIBLE
                        if (task.isSuccessful) {
                            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToEmployeeListFragment())
                        } else {
                            val toast = Toast.makeText(requireActivity(), "Registration Failed. ${task.exception?.message}", Toast.LENGTH_SHORT)
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                            toast.show()
                        }

                    }
            }
        }
    }

    private fun validateInput(email: String, pass: String): Boolean {

        if (email.isBlank()) {
            register_user_email_container.error = "Please enter a valid email address"
            return false
        }

        if (pass.isBlank()) {
            register_user_password_container.error = "Please enter password"
            return false
        }

        if (pass.length < 8) {
            register_user_password_container.error = "Password must be 8 character long."
            return false
        }

        return true
    }

}