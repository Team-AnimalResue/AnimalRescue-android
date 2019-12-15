package com.team.animalrescue.common

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import com.team.animalrescue.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }

    fun showKeyboard(editText: EditText) {
        editText.requestFocus()
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(editText, 0)
    }

    fun showShortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showLongToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showLongSnackbar(layoutContainer: View, message: String) {
        Snackbar.make(layoutContainer, message, Snackbar.LENGTH_LONG).show()
    }

    fun showLongSnackbar(layoutContainer: View, @StringRes message: Int) {
        Snackbar.make(layoutContainer, message, Snackbar.LENGTH_LONG).show()
    }

    fun showShortSnackbar(layoutContainer: View, message: String) {
        Snackbar.make(layoutContainer, message, Snackbar.LENGTH_SHORT).show()
    }

}
