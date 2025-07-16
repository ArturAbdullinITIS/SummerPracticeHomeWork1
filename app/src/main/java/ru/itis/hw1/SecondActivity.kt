package ru.itis.hw1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.itis.hw1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private var viewBinding: ActivitySecondBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewBinding = ActivitySecondBinding.inflate(layoutInflater)
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
           val textWatcher = object : TextWatcher {
               override fun afterTextChanged(s: Editable?) {
                   submitButton.isEnabled = validEmail(emailEdit.text.toString()) && validPassword(passwordEdit.text.toString())

               }

               override fun beforeTextChanged(
                   s: CharSequence?,
                   start: Int,
                   count: Int,
                   after: Int
               ) {

               }

               override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

               }
           }
            emailEdit.addTextChangedListener(textWatcher)
            passwordEdit.addTextChangedListener(textWatcher)

            submitButton?.setOnClickListener {
                val intent = Intent(this@SecondActivity, ThirdActivity::class.java).apply {
                    putExtra("Email", emailEdit.text.toString())
                    putExtra("Password", passwordEdit.text.toString())
                }
                startActivity(intent)
            }
            backButton?.setOnClickListener {
                startActivity(Intent(this@SecondActivity, FirstActivity::class.java))
            }
        }
    }

    private fun validEmail(email : String) : Boolean {
        return email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())
    }
    private fun validPassword(password : String) : Boolean {
        if (password.length < 8) {
            return false
        }
        if (!password.any {it.isDigit()}) return false
        if (!password.any {it.isUpperCase()}) return false
        if (!password.any {it.isLowerCase()}) return false
        return true
    }
}