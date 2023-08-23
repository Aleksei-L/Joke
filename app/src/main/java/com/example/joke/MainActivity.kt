package com.example.joke

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val button = findViewById<Button>(R.id.button)
		button.setOnClickListener { onClick() }
	}

	private fun onClick() {
		val intent = Intent(this, DelayedMessageService::class.java)
		intent.putExtra(DelayedMessageService.EXTRA_MESSAGE, resources.getString(R.string.response))
		startService(intent)
	}
}
