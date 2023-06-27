package com.example.cloud_firestore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.cloud_firestore.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var bind : ActivityMainBinding
    lateinit var auth: FirebaseAuth
    var c = 0
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        firestore = FirebaseFirestore.getInstance()
        bind.signp.setOnClickListener {
            c++
            val data = mapOf(
                "name" to bind.editTextTextPersonName.text.toString(),
               "mail" to bind.editTextTextEmailAddress.text.toString(),
               "pass" to bind.editTextNumberPassword.text.toString(),
                "add" to bind.editTextTextadress2.text.toString()
            )
            val s = bind.editTextNumberPassword.text.toString()
            val nm = bind.editTextTextPersonName.text.toString()
            firestore.collection("user-${nm}").document("user-${s}").set(data).addOnCompleteListener {
                 Toast.makeText(this,"Complete",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {it->
                Toast.makeText(this,"Complete ${it}",Toast.LENGTH_SHORT).show()
            }
        }
        bind.login.setOnClickListener {
            startActivity(Intent(this,login::class.java))
        }
    }


}