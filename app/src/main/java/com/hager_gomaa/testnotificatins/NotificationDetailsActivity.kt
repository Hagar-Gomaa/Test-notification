package com.hager_gomaa.testnotificatins

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NotificationDetailsActivity : AppCompatActivity() {
    lateinit var textViewNotificationDetails:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_details2)
        textViewNotificationDetails = findViewById(R.id.textViewNotificationDetails);
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        textViewNotificationDetails.text=intent.getStringExtra("count")
    }
}