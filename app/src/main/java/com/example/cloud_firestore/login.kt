package com.example.cloud_firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.cloud_firestore.databinding.ActivityLoginBinding
import com.google.firebase.firestore.FirebaseFirestore

class login : AppCompatActivity() {
    lateinit var bind:ActivityLoginBinding
    lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bind.root)
        firestore = FirebaseFirestore.getInstance()
        bind.button.setOnClickListener {
            val pasid = bind.editTextNumberPassword2.text.toString()
            val nm = bind.editTextTextEmailAddress2.text.toString()
            val ref = firestore.collection("user-${nm}").document("user-${pasid}")
            ref.get().addOnSuccessListener { du->
                if(du.exists()){
                    Toast.makeText(this,"Succes",Toast.LENGTH_SHORT).show()
                    val nm = du.getString("name")
                    val em = du.getString("add")
                    bind.name.text = nm.toString()
                    bind.adress.text = em.toString()
                }
                else{
                    Toast.makeText(this,"Doesnot Exit",Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener {
                Toast.makeText(this,"Failure",Toast.LENGTH_SHORT).show()
            }
        }
        bind.delet.setOnClickListener {
            val pasid = bind.editTextNumberPassword2.text.toString()
            val nm = bind.editTextTextEmailAddress2.text.toString()
            val ref = firestore.collection("user-${nm}").document("user-${pasid}")
            ref.delete().addOnSuccessListener {
                Toast.makeText(this,"Delet data form the database",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {it->
                Toast.makeText(this,"Failure to delete ${it}",Toast.LENGTH_SHORT).show()
            }
        }
    }
}