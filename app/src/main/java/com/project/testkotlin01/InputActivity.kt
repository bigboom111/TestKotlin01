package com.project.testkotlin01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_input.*

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
    }

    fun cancelClick(view: View) {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    fun saveClick(view: View) {
        val taskName = txt_Input.text.toString()
        if (taskName != ""){
            val data = Intent()
            data.putExtra("TASK_NAME_KEY" , taskName)
            setResult(Activity.RESULT_OK , data)
        }else{
            setResult(Activity.RESULT_CANCELED)
        }
        finish()
    }
}