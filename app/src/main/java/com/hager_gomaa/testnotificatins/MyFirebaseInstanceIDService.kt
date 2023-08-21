package com.hager_gomaa.testnotificatins

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.RemoteMessage

import android.app.PendingIntent

import com.google.firebase.messaging.FirebaseMessagingService

class MyFireBaseMessagingService : FirebaseMessagingService() {

    private val TAG = MyFireBaseMessagingService::class.java.simpleName

    enum class PUSH_NOTIFICATION_SOURCE {
        CONSOLE, API_WITHOUT_NOTIFICATION, API_WITH_NOTIFICATION, UNKNOWN_SOURCE
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.i(getString(R.string.DEBUG_TAG), "New token: $token")
        // Making an API call - Thread, Volley, okHttp, Retrofit
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val notificationSource = getNotificationSource(remoteMessage)

        Log.i(getString(R.string.DEBUG_TAG), "Remote Message received from : $notificationSource")

        when (notificationSource) {
            PUSH_NOTIFICATION_SOURCE.CONSOLE -> {
                (application as MyApplication).triggerNotificationWithBackStack(
                    NotificationDetailsActivity::class.java,
                    getString(R.string.NEWS_CHANNEL_ID),
                    remoteMessage.notification?.title,
                    remoteMessage.notification?.body,
                    remoteMessage.notification?.title,
                    NotificationCompat.PRIORITY_HIGH,
                    false,
                    0,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            }
            PUSH_NOTIFICATION_SOURCE.API_WITH_NOTIFICATION -> {
                (application as MyApplication).triggerNotificationWithBackStack(
                    NotificationDetailsActivity::class.java,
                    getString(R.string.NEWS_CHANNEL_ID),
                    remoteMessage.notification?.title,
                    remoteMessage.notification?.body,
                    "This notification is from FCM API call with notification title and body",
                    NotificationCompat.PRIORITY_HIGH,
                    false,
                   0,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            }
            PUSH_NOTIFICATION_SOURCE.API_WITHOUT_NOTIFICATION -> {
                (application as MyApplication).triggerNotificationWithBackStack(
                    NotificationDetailsActivity::class.java,
                    getString(R.string.NEWS_CHANNEL_ID),
                    "Random notification Title",
                    "Random notification body",
                    "This notification is from FCM API call without notification title and body",
                    NotificationCompat.PRIORITY_HIGH,
                    false,
                   0,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            }
            PUSH_NOTIFICATION_SOURCE.UNKNOWN_SOURCE -> {
                Log.i(TAG, "Since it's an unknown source, don't want to do anything")
            }
            else -> {
                // Do nothing for other cases
            }
        }
    }

    private fun getNotificationSource(remoteMessage: RemoteMessage): PUSH_NOTIFICATION_SOURCE {
        val notification = remoteMessage.notification
        val data = remoteMessage.data

        return when {
            notification != null && data != null -> {
                if (data.size == 0) {
                    PUSH_NOTIFICATION_SOURCE.CONSOLE
                } else {
                    PUSH_NOTIFICATION_SOURCE.API_WITH_NOTIFICATION
                }
            }
            remoteMessage.data != null -> {
                PUSH_NOTIFICATION_SOURCE.API_WITHOUT_NOTIFICATION
            }
            else -> {
                PUSH_NOTIFICATION_SOURCE.UNKNOWN_SOURCE
            }
        }
    }
}

//class MyFirebaseMessagingService : FirebaseMessagingService() {
//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        super.onMessageReceived(remoteMessage)
//        Log.e("NotificationContent",remoteMessage.notification!!.title.toString())
//        Log.e("NotificationContent",remoteMessage.notification?.body.toString())
//        Log.e("NotificationContent",remoteMessage.data!!.toString())
////        Log.e("NotificationContent",remoteMessage.data?.body.toString())
//
////        getFirebaseMessage(
////            remoteMessage.notification!!.title, remoteMessage.notification!!
////                .body
////        )
//
//
//    }
//
//    fun getFirebaseMessage(title: String?, msg: String?) {
//        val builder: NotificationCompat.Builder =
//            NotificationCompat.Builder(this, "myFirebaseChannel")
//                .setSmallIcon(R.drawable.baseline_notifications_active_24)
//                .setContentTitle(title)
//                .setContentText(msg)
//                .setAutoCancel(true)
//        val manager = NotificationManagerCompat.from(this)
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return
//        }
//        manager.notify(101, builder.build())
//    }


//}




