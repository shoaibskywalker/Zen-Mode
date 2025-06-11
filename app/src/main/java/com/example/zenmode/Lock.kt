package com.example.zenmode

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zenmode.databinding.ActivityLockBinding

class Lock : AppCompatActivity() {
    private lateinit var binding: ActivityLockBinding
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLockBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val unlockTime = intent.getLongExtra("UNLOCK_TIME", 0L)
        val delay = unlockTime - System.currentTimeMillis()
        if (delay > 0) {
            countDownTimer = object : CountDownTimer(delay, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val hours = millisUntilFinished / (1000 * 60 * 60)
                    val minutes = (millisUntilFinished / (1000 * 60)) % 60
                    val seconds = (millisUntilFinished / 1000) % 60
                    binding.timerText.text = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                }
                override fun onFinish() {
                    finish()
                }
            }.start()
        } else {
            finish()
        }
        /*val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable {
            finish()
        }
        val delay = unlockTime - System.currentTimeMillis()
        if (delay > 0) handler.postDelayed(runnable, delay)
        else finish()*/
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        // Do nothing
    }
}