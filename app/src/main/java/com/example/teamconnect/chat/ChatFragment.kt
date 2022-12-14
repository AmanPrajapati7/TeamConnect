package com.example.teamconnect.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teamconnect.R
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.message_item.*

const val MESSAGE_BASE_PATH = "messages"

class ChatFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: FirebaseDatabase
    private lateinit var adapter: FirebaseRecyclerAdapter<UserMessage, ViewHolder?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        send_message.setOnClickListener {
            val messageText = message_text.text.toString()
            if (messageText.isBlank()) {
                Toast.makeText(
                    requireActivity(),
                    "Please enter a message to send",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val ref = dbRef.getReference(MESSAGE_BASE_PATH).push()
                val userMessage = UserMessage(auth.currentUser?.email ?: "Unknown", messageText)

                ref.setValue(userMessage).addOnSuccessListener {
                    message_text.setText("")
                    messages.smoothScrollToPosition(adapter.itemCount)
                }.addOnFailureListener { ex: Exception ->
                    Toast.makeText(
                        requireActivity(),
                        "Failed to send the message ${ex.message.toString()}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        messages.layoutManager = LinearLayoutManager(requireActivity())
        setupMessageList()

    }

    private fun setupMessageList() {
        val query: Query = dbRef.reference.child(MESSAGE_BASE_PATH)

        val options:
                FirebaseRecyclerOptions<UserMessage> =
            FirebaseRecyclerOptions.Builder<UserMessage>()
                .setQuery(query) {
                    UserMessage(
                        it.child("email").value.toString(), it.child("message").value.toString()
                    )
                }.build()

        adapter = object : FirebaseRecyclerAdapter<UserMessage, ViewHolder?>(options) {

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.message_item, parent, false)
                return ViewHolder(view)
            }

            override fun onBindViewHolder(
                holder: ViewHolder,
                position: Int,
                model: UserMessage
            ) {
                holder.bind(model)
            }
        }

        messages.adapter = adapter
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(userMessage: UserMessage) {
            with(userMessage) {
                user_email.text = email
                user_message.text = message
            }
        }
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

}