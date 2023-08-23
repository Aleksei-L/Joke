package com.example.joke

import android.app.IntentService
import android.content.Intent
import android.util.Log
import java.lang.Thread.sleep

class DelayedMessageService : IntentService("DelayedMessageService") {
	override fun onHandleIntent(p0: Intent?) {
		synchronized(this) {
			try {
				sleep(10000)
			} catch (e: InterruptedException) {
				e.printStackTrace()
			}
		}
		val text: String? = p0?.getStringExtra(EXTRA_MESSAGE)
		if (text != null)
			showText(text)
	}

	private fun showText(text: String) = Log.v("DelayedMessageService", "The message is: $text")

	companion object {
		const val EXTRA_MESSAGE = "message"
	}
}
