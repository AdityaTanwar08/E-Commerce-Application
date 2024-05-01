package com.example.ecommerceapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecommerceapp.databinding.ActivitySignUpBinding
//import com.example.firebaseauthentication.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private val binding : ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
     private lateinit var auth : FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        // Initialising Firebase auth
        auth = FirebaseAuth.getInstance() // isse authentication use kar skte hai



        binding.registerbtn.setOnClickListener {
            // get text from editText field
            val email = binding.email.text.toString()
            val username = binding.username.text.toString()
            val password = binding.password1.text.toString()
            val repeatpassword = binding.repassword.text.toString()

            // check if any field is blank

            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || repeatpassword.isEmpty()){
                Toast.makeText(this,"Please Fill All The Details", Toast.LENGTH_SHORT).show()
            }else if( password != repeatpassword){
                Toast.makeText(this,"Repeat password must be same", Toast.LENGTH_SHORT).show()
            }
            else{
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) { task->
                    if (task.isSuccessful){
                        Toast.makeText(this,"Registration Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,SignInActivity::class.java))
                        finish()
                    }
                    else{
                        Toast.makeText(this,"Registration Failed : ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

    }
}