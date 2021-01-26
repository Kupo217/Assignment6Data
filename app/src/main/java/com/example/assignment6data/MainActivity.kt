package com.example.assignment6data

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "")
        val firstName = sharedPreferences.getString("firstName", "")
        val lastName = sharedPreferences.getString("lastName", "")
        val age = sharedPreferences.getInt("age", 0)
        val address = sharedPreferences.getString("address", "")

        emailEditText.setText(email)
        firstNameEditText.setText(firstName)
        lastNameEditText.setText(lastName)
        if (age == 0) {
            ageEditText.setText("")
        } else {
            ageEditText.setText(age.toString())
        }
        addressEditText.setText(address)
    }

    fun save(view: View) {

        val email = emailEditText.text.toString()
        val firstName = firstNameEditText.text.toString()
        val lastName = lastNameEditText.text.toString()
        val ageValue = ageEditText.text.toString()
        val address = addressEditText.text.toString()

        if (email.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty() && ageValue.isNotEmpty() && address.isNotEmpty()) {
            val age = ageValue.toInt()
            if (age < 0 || age > 150 || age == 0) {
                Toast.makeText(this, "Be more Serious!!! :DDD \n Change Age Value :DDD", Toast.LENGTH_LONG).show()
            }else{
                val edit = sharedPreferences.edit()
                edit.putString("email", email)
                edit.putString("firstName", firstName)
                edit.putString("lastName", lastName)
                edit.putInt("age", age)
                edit.putString("address", address)
                edit.apply()
                Toast.makeText(this, "Data Saved!", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        }
    }

    fun delete(view: View) {
        val edit = sharedPreferences.edit()
        edit.clear()
        edit.apply()
        Toast.makeText(this, "Data Deleted!", Toast.LENGTH_SHORT).show()
        emailEditText.setText("")
        firstNameEditText.setText("")
        lastNameEditText.setText("")
        ageEditText.setText("")
        addressEditText.setText("")
    }
}