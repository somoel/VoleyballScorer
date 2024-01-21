package com.somoel.voleyballscorer

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        val tbview: TextView = findViewById(R.id.teamBlueScore)
        val trview: TextView = findViewById(R.id.teamRedScore)


        setLongClickListener(tbview)
        setLongClickListener(trview)

    }

    private fun setLongClickListener(textView: TextView) {
        textView.setOnLongClickListener {
            restPoint(textView)
            true
        }
    }

    fun sumPoint(view: View) {
        if (view is TextView) {
            val cadenaActual: String = view.text.toString()

            try {
                var valorActual = cadenaActual.toInt()
                valorActual += 1

                // Actualiza el TextView con el nuevo valor
                view.text = valorActual.toString()
            } catch (e: NumberFormatException) {
                // Manejar la excepción si la cadena no puede ser convertida a entero
                e.printStackTrace()
            }
        }
    }

    fun restPoint(textView: TextView) {
        val cadenaActual: String = textView.text.toString()

        try {
            var valorActual = cadenaActual.toInt()
            if (valorActual > 0) {
                valorActual -= 1
            }

            // Actualiza el TextView con el nuevo valor
            textView.text = valorActual.toString()
        } catch (e: NumberFormatException) {
            // Manejar la excepción si la cadena no puede ser convertida a entero
            e.printStackTrace()
        }
    }

    fun resetPoints(view: View){
        val tbview: TextView = findViewById(R.id.teamBlueScore)
        val trview: TextView = findViewById(R.id.teamRedScore)
        tbview.text = "0"
        trview.text = "0"
    }

    fun switchPoints(view: View){
        val tbview: TextView = findViewById(R.id.teamBlueScore)
        val trview: TextView = findViewById(R.id.teamRedScore)
        val temp: String = tbview.text.toString()
        tbview.text = trview.text.toString()
        trview.text = temp
    }
}