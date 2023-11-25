package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener {

            showNotification(applicationContext,"Hello","Woorld")


        }

    }




}

fun showNotification(context: Context, title: String, content: String) {
    // Créer un identifiant unique pour la notification
    val notificationId = 1

    // Récupérer le NotificationManager
    val notificationManager =
        getSystemService(context, NotificationManager::class.java) as NotificationManager

    // Vérifier si le canal de notification doit être créé (nécessaire pour les versions Android >= 8.0)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "my_channel_id"
        val channel = NotificationChannel(
            channelId,
            "My Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }

    // Créer l'objet NotificationCompat.Builder
    val builder = NotificationCompat.Builder(context, "my_channel_id")
        .setSmallIcon(androidx.core.R.drawable.notification_bg)
        .setContentTitle(title)
        .setContentText(content)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    // Afficher la notification
    notificationManager.notify(notificationId, builder.build())
}