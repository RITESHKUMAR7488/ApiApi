package com.example.apiapi.uis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.apiapi.R
import com.example.apiapi.databinding.ActivitySignupBinding
import com.example.apiapi.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var auth: FirebaseAuth
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initialize authentication

        auth = FirebaseAuth.getInstance()



        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this@signup, R.layout.activity_signup)
        with(binding) {
            btnSignIn.setOnClickListener {
                startActivity(Intent(this@signup, LoginActivity::class.java))
                finish()
            }
            btnSignup.setOnClickListener {

                val email = binding.userEmail.text.toString()
                val username = binding.userName.text.toString()
                val password = binding.userPasswords.text.toString()
                val repeatPassword = binding.userRepeat.text.toString()

                //check if any field is blank
                if (email.isBlank() || username.isBlank() || password.isBlank() || repeatPassword.isBlank()) {
                    Toast.makeText(this@signup, "please fill all the blanks", Toast.LENGTH_SHORT)
                        .show()
                } else if (password != repeatPassword) {
                    Toast.makeText(this@signup, "password is nit matching", Toast.LENGTH_SHORT)
                        .show()


                } else {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this@signup) { task ->

                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this@signup,
                                    "Registration is succesfull",
                                    Toast.LENGTH_SHORT
                                ).show()
                                // it is for storing data in firebase database
                                val user = User(username, email, password)
                                database = FirebaseDatabase.getInstance().getReference("Users")
                                database.child(username).setValue(user)

                                val db = Firebase.firestore

                                startActivity(Intent(this@signup, LoginActivity::class.java))
                                finish()






                            } else {
                                Toast.makeText(
                                    this@signup,
                                    "Registration failed :${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }

//                            db.collection("users2")
//                                .add(user)
//                                .addOnSuccessListener { documentReference ->
//                                    Log.d("response", "DocumentSnapshot added with ID: ${documentReference.id}")
//                                }
//                                .addOnFailureListener { e ->
//                                    Log.w("response", "Error adding document", e)
//                                }
                        }

                }






            }

        }
    }
}