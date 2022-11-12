package com.example.labcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.Timer)
        val buttonStart = findViewById<Button>(R.id.Start)
        val buttonStop = findViewById<Button>(R.id.Stop)

        var isRunning = false

        var i = 0
        var flag = true

        var job: Job? = null

        buttonStart.setOnClickListener {
            isRunning = true
            if(job == null) {
                job = lifecycleScope.launch {

                    while (isRunning) {
                        delay(1000)
                        if (i <= 0) {
                            flag = false
                        } else if (i >= 10) {
                            flag = true
                        }
                        if (flag) {
                            i--
                        } else {
                            i++
                        }
                        textView.text = i.toString()
                    }
                }
            }
        }

        buttonStop.setOnClickListener {
            isRunning = false
            job = null

        }
    }
}