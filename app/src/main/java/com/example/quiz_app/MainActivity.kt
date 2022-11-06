package com.example.quiz_app

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private var res = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_reset).setOnClickListener{

        }

        findViewById<Button>(R.id.btn_submit).setOnClickListener{

            val current = LocalDateTime.now()
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS")
            val date = current.format(dateFormatter)
            val time = current.format(timeFormatter)
            val message = if(res > 0)
                "Congratulations! You submitted on current $date and $time, You got $res%"
            else "Try again."
            val title = "Quiz App Result"
            displayDialog(title, message)
        }
    }



    fun RadioButtonSelect(view: View){
        if(view is RadioButton){
            var cheked = view.isChecked
            when(view.getId()){
                R.id.q12  -> if(cheked) res+=50
            }
        }
    }


    private fun reset(){
        res = 0
        findViewById<RadioGroup>(R.id.radio_group).clearCheck()
        findViewById<CheckBox>(R.id.q21).isChecked = false
        findViewById<CheckBox>(R.id.q22).isChecked = false
        findViewById<CheckBox>(R.id.q23).isChecked = false
    }

    fun CheckBoxSelected(view: View){
        if(view is CheckBox){
            var cheked = view.isChecked
            when(view.getId()){ R.id.q22  -> if(cheked) res+=50
            }
        }
    }


    private fun displayDialog(title: String, message: String){
        val alertDialog: AlertDialog = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton("OK")
                { dialog, _ ->
                    reset()
                    dialog.dismiss()
                }
            }
            builder.setMessage(message)
                .setTitle(title)
            builder.create()
        }
        alertDialog.show()
    }
}