package com.hager_gomaa.testnotificatins


import android.R
import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging


class MyApplication : Application() {
    var myAppsNotificationManager: MyAppsNotificationManager? = null
    override fun onCreate() {
        super.onCreate()
        myAppsNotificationManager = MyAppsNotificationManager.getInstance(this)
        myAppsNotificationManager?.registerNotificationChannelChannel(
            getString(com.hager_gomaa.testnotificatins.R.string.NEWS_CHANNEL_ID),
            getString(com.hager_gomaa.testnotificatins.R.string.CHANNEL_NEWS),
            getString(com.hager_gomaa.testnotificatins.R.string.CHANNEL_DESCRIPTION),
        )
        FirebaseMessaging.getInstance().isAutoInitEnabled
//        FirebaseInstanceId.getInstance().getInstanceId()
//            .addOnCompleteListener(OnCompleteListener<Any> { task ->
//                if (!task.isComplete) {
//                    Log.i(getString(R.string.DEBUG_TAG), " Task Filed")
//                    return@OnCompleteListener
//                }
//                Log.i(
//                    getString(R.string.DEBUG_TAG),
//                    " The completed result: " + task.result.getToken()
//                )
//                //Making an API call - Thread, Volley, okHttp, Retrofit
//            })
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
        myAppsNotificationManager?.triggerNotification(
            targetNotificationActivity,
            channelId,
            title,
            text,
            bigText,
            priority,
            autoCancel,
            notificationId,
            pendingIntentFlag
        )
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
        myAppsNotificationManager?.triggerNotification(
            targetNotificationActivity,
            channelId,
            title,
            text,
            bigText,
            priority,
            autoCancel,
            notificationId
        )
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
        myAppsNotificationManager?.triggerNotificationWithBackStack(
            targetNotificationActivity,
            channelId,
            title,
            text,
            bigText,
            priority,
            autoCancel,
            notificationId,
            pendingIntentFlag
        )
    }

    fun updateNotification(
        targetNotificationActivity: Class<*>?,
        title: String?,
        text: String?,
        channelId: String?,
        notificationId: Int,
        bigpictureString: String?,
        pendingIntentflag: Int
    ) {
        myAppsNotificationManager?.updateWithPicture(
            targetNotificationActivity,
            title,
            text,
            channelId,
            notificationId,
            bigpictureString,
            pendingIntentflag
        )
    }

    fun cancelNotification(notificaitonId: Int) {
        myAppsNotificationManager?.cancelNotification(notificaitonId)
    }

    companion object {
        private val TAG = MyApplication::class.java.simpleName
    }
}
