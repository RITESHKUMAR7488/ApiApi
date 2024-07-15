package com.example.apiapi.uis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.apiapi.R
import com.example.apiapi.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onStart() {
        super.onStart()

        //check if user is already logged in
        val currentUser: FirebaseUser? = auth.currentUser

        if(currentUser!=null){
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_login)
        with(binding) {

            auth=FirebaseAuth.getInstance()



            btnSignup.setOnClickListener {
                val intent = Intent(this@LoginActivity, signup::class.java)
                startActivity(intent)
            }
            btnSignin.setOnClickListener {
                val userName = binding.userEmail.text.toString()
                val password = binding.userPasswords.text.toString()
                if(userName.isBlank()||password.isBlank()){
                    Toast.makeText(this@LoginActivity,"please fill all the blanks", Toast.LENGTH_SHORT).show()
                }else{
                    auth.signInWithEmailAndPassword(userName, password)
                        .addOnCompleteListener(this@LoginActivity) { task ->
                            if (task.isSuccessful) {
                                Log.d("isSuccesful", "true")
                                Toast.makeText(this@LoginActivity,"Login is succesfull", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finish()

                            }
                            else{
                                Log.d("isSuccesful", "false")
                                Toast.makeText(this@LoginActivity,"Login failed :${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }


                }

                }


            }

        }
    }
}