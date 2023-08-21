package com.hager_gomaa.testnotificatins

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                var token = task.result
                Log.e("token",token)
                if (!task.isSuccessful) {
                    Log.e("errorToken","errorToken")
                }
            }

    }
}