package com.example.lesson19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.lesson19.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    private var login: String? = "empty"
    private var password: String? = "empty"
    private var name: String? = "empty"
    private var surname: String? = "empty"
    private var avatarImageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101 && resultCode == RESULT_OK && data != null){
            val l = data?.getStringExtra("login")
            val r = data?.getStringExtra("password")
            if (login == l && password == r) {
                bindingClass.imgAvatar.setImageResource(avatarImageId)
                bindingClass.tvName.text = name
                bindingClass.tvSurname.text = surname
                bindingClass.tvLogin.text = login
                bindingClass.userList.visibility = View.VISIBLE
            } else {
                bindingClass.errorAuth.visibility = View.VISIBLE
                bindingClass.imgAvatar.visibility = View.VISIBLE
                bindingClass.errorAuth.text = "Такого аккаунта не существует"
            }
        }else if (requestCode == 100 && resultCode == RESULT_OK && data != null){
            login = data?.getStringExtra("login")!!
            password = data?.getStringExtra("password")!!
            name = data?.getStringExtra("name")!!
            surname = data?.getStringExtra("surname")!!
            avatarImageId = data.getIntExtra("avatar", 0)
            bindingClass.imgAvatar.setImageResource(avatarImageId)
            bindingClass.tvName.text = login
            bindingClass.tvSurname.text = surname
            bindingClass.tvLogin.text = login
            bindingClass.userList.visibility = View.VISIBLE
            bindingClass.auth.text = "Выйти"
            bindingClass.reg.visibility = View.INVISIBLE

        }
    }

    fun onClickReg(view: View){
            val i = Intent(this, EditUser::class.java)
            i.putExtra("signState", "reg")
            startActivityForResult(i, 100)
    }

    fun onClickAuth(view: View) {


        if (bindingClass.imgAvatar.isVisible && bindingClass.errorAuth.text.toString() != "Такого аккаунта не существует") {

            bindingClass.imgAvatar.visibility = View.GONE
            bindingClass.userList.visibility = View.GONE
            bindingClass.auth.text = "Войти"
            bindingClass.reg.visibility = View.VISIBLE

        } else {
            val i = Intent(this, EditUser::class.java)
            i.putExtra("signState", "auth")
            startActivityForResult(i, 101)
        }
    }
}