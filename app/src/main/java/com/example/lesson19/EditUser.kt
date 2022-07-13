package com.example.lesson19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.lesson19.databinding.ActivityEditBinding

class EditUser : AppCompatActivity() {
    lateinit var bindingClass: ActivityEditBinding
    private var opType: String? = "empty"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityEditBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        opType = intent.getStringExtra("signState")
        if (opType == "auth"){
            bindingClass.edName.visibility = View.GONE
            bindingClass.edSurname.visibility = View.GONE
            bindingClass.tvAvr.visibility = View.GONE
            bindingClass.avrSelect.visibility = View.GONE
        }

        bindingClass.avrSelect.setOnClickListener {
            bindingClass.avrImg.visibility = View.VISIBLE
        }

        bindingClass.myBtn.setOnClickListener {

            if (opType == "reg") {
                intent.putExtra("name", bindingClass.edName.text.toString())
                intent.putExtra("surname", bindingClass.edSurname.text.toString())
                intent.putExtra("login", bindingClass.edLogin.text.toString())
                intent.putExtra("password", bindingClass.edPassword.text.toString())
                if (bindingClass.avrImg.isVisible) intent.putExtra("avatar", R.drawable.avatar)
                setResult(RESULT_OK, intent)
                finish()
            }else if (opType == "auth"){
                intent.putExtra("login", bindingClass.edLogin.text.toString())
                intent.putExtra("password", bindingClass.edPassword.text.toString())
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}