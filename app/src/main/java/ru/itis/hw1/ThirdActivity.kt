package ru.itis.hw1

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.itis.hw1.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private var viewBinding: ActivityThirdBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding = null
    }
    private fun initView() {
        viewBinding?.apply {
            emailView.text = intent.getStringExtra("Email") ?: ""
            passwordView.text = intent.getStringExtra("Password") ?: ""
            backButton?.setOnClickListener {
                startActivity(Intent(this@ThirdActivity, SecondActivity::class.java))
            }
        }
    }
}