package com.hager_gomaa.testnotificatins


import android.Manifest
import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MyAppsNotificationManager private constructor(private val context: Context) {
    private val notificationManagerCompat: NotificationManagerCompat = NotificationManagerCompat.from(context)
    private val notificationManager: NotificationManager

    init {
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun registerNotificationChannelChannel(
        channelId: String?,
        channelName: String?,
        channelDescription: String?
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.description = channelDescription
            val notificationManager = context.getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    fun triggerNotification(
        targetNotificationActivity: Class<*>?,
        channelId: String?,
        title: String?,
        text: String?,
        bigText: String?,
        priority: Int,
        autoCancel: Boolean,
        notificationId: Int
    ) {
        val intent = Intent(context, targetNotificationActivity)
        intent.putExtra("count", title)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(
            context,
            channelId!!
        )
            .setSmallIcon(R.drawable.stat_notify_chat)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.stat_notify_chat))
            .setContentTitle(title)
            .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setChannelId(channelId)
            .setAutoCancel(true)
        val notificationManagerCompat = NotificationManagerCompat.from(
            context
        )
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManagerCompat.notify(notificationId, builder.build())
    }

    fun triggerNotification(
        targetNotificationActivity: Class<*>?,
        channelId: String?,
        title: String?,
        text: String?,
        bigText: String?,
        priority: Int,
        autoCancel: Boolean,
        notificationId: Int,
        pendingIntentFlag: Int
    ) {
        val intent = Intent(context, targetNotificationActivity)
        intent.putExtra("count", title)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, pendingIntentFlag)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(
            context,
            channelId!!
        )
            .setSmallIcon(R.drawable.stat_notify_chat)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.stat_notify_chat))
            .setContentTitle(title)
            .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setChannelId(channelId)
            .setAutoCancel(true)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManagerCompat.notify(notificationId, builder.build())
    }

    fun triggerNotificationWithBackStack(
        targetNotificationActivity: Class<*>?,
        channelId: String?,
        title: String?,
        text: String?,
        bigText: String?,
        priority: Int,
        autoCancel: Boolean,
        notificationId: Int,
        pendingIntentFlag: Int
    ) {
        val intent = Intent(context, targetNotificationActivity)
        val taskStackBuilder = TaskStackBuilder.create(
            context
        )
        taskStackBuilder.addNextIntentWithParentStack(intent)
        intent.putExtra("count", title)
        val pendingIntent = taskStackBuilder.getPendingIntent(0, pendingIntentFlag)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(
            context,
            channelId!!
        )
            .setSmallIcon(R.drawable.stat_notify_chat)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.stat_notify_chat))
            .setContentTitle(title)
            .setContentText(text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setChannelId(channelId)
            .setOngoing(false)
            .setAutoCancel(false)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManagerCompat.notify(notificationId, builder.build())
    }

    fun updateWithPicture(
        targetNotificationActivity: Class<*>?,
        title: String?,
        text: String?,
        channelId: String?,
        notificationId: Int,
        bigpictureString: String?,
        pendingIntentflag: Int
    ) {
        val intent = Intent(context, targetNotificationActivity)
        intent.putExtra("count", title)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, pendingIntentflag)
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(
            context,
            channelId!!
        )
            .setSmallIcon(R.drawable.stat_notify_chat)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.stat_notify_chat))
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setChannelId(channelId)
            .setAutoCancel(true)
        val androidImage = BitmapFactory.decodeResource(context.resources, R.drawable.stat_notify_chat)
        builder.setStyle(
            NotificationCompat.BigPictureStyle().bigPicture(androidImage)
                .setBigContentTitle(bigpictureString)
        )
        notificationManager.notify(notificationId, builder.build())
    }

    fun cancelNotification(notificationId: Int) {
        notificationManager.cancel(notificationId)
    }

    companion object {
        private var instance: MyAppsNotificationManager? = null
        fun getInstance(context: Context): MyAppsNotificationManager? {
            if (instance == null) {
                instance = MyAppsNotificationManager(context)
            }
            return instance
        }
    }
}
