package com.taghavi.repeatnotificationpractice

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class AlarmReceiver : BroadcastReceiver() {
    val NOTIFICATION_ID = 1
    override fun onReceive(context: Context, intent: Intent?) {
        Log.i("TAGTAG", "onReceive: Triggered")
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java,
        ) as NotificationManager

        notificationManager.sendReminderNotification(
            applicationContext = context,
            channelId = context.getString(R.string.reminder_notification_channel_id),
        )

        RemindersManager.startReminder(context.applicationContext)
    }

    private fun NotificationManager.sendReminderNotification(
        applicationContext: Context,
        channelId: String,
    ) {
        val contentIntent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            1,
            contentIntent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        val builder = NotificationCompat.Builder(
            applicationContext,
            channelId,
        )
            .setContentTitle(applicationContext.getString(R.string.title_notification_reminder))
            .setContentText(applicationContext.getString(R.string.desctiption_notification_reminder))
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(applicationContext.getString(R.string.desctiption_notification_reminder))
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        notify(NOTIFICATION_ID, builder.build())
    }
}