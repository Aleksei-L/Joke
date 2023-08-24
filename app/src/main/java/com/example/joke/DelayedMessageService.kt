package com.example.joke

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import java.lang.Thread.sleep

class DelayedMessageService : IntentService("DelayedMessageService") {
	private val NOTIFICATION_ID = 1
	private val CHANNEL_ID = "my_channel_01"

	override fun onHandleIntent(p0: Intent?) {
		synchronized(this) {
			try {
				sleep(1000)
			} catch (e: InterruptedException) {
				e.printStackTrace()
			}
		}
		val text: String? = p0?.getStringExtra(EXTRA_MESSAGE)
		if (text != null)
			showText(text)
	}

	private fun showText(text: String) {
		val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

		val myChannel =
			NotificationChannel(
				"my_channel_01",
				"myChannel",
				NotificationManager.IMPORTANCE_DEFAULT
			)
		myChannel.description = "description"
		myChannel.enableLights(true)
		myChannel.enableVibration(true)
		notificationManager.createNotificationChannel(myChannel)

		val notification = Notification.Builder(this, CHANNEL_ID)
			.setSmallIcon(android.R.drawable.sym_def_app_icon)
			.setContentTitle(getString(R.string.question))
			.setContentText(text)
		val actionIntent = Intent(this, MainActivity::class.java)
		val actionPendingIntent =
			PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT)
		notification.setContentIntent(actionPendingIntent)

		notificationManager.notify(NOTIFICATION_ID, notification.build())
	}

	companion object {
		const val EXTRA_MESSAGE = "message"
	}
}
