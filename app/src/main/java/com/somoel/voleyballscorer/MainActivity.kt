package com.somoel.voleyballscorer

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION", "UNUSED_PARAMETER")
class MainActivity : AppCompatActivity() {

    // At app invocation
    override fun onCreate(savedInstanceState: Bundle?) {

        // Start configs
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE // Set landscape mode
        // Set fullscreen mode
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        // Scores
        val tbview: TextView = findViewById(R.id.teamBlueScore)
        val trview: TextView = findViewById(R.id.teamRedScore)

        // Ideal screen size for score
        val density = resources.displayMetrics.density
        val textHeightPixels = resources.displayMetrics.heightPixels * 0.95f
        val textWidthPixels = resources.displayMetrics.widthPixels * 0.5f
        val textSizeInSp = textHeightPixels / density

        for (tview in listOf(trview, tbview)) {
            tview.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeInSp)
            tview.width = textWidthPixels.toInt()

            setLongClickListener(tview) // Long Click Listener for minus point
        }

    }

    /*
    LongClickListener for rest score
     */
    private fun setLongClickListener(textView: TextView) {
        textView.setOnLongClickListener {
            restPoint(textView)
            true
        }
    }

    /*
    Sum a point in the counter
     */
    fun sumPoint(view: View) {
        if (view is TextView) {
            val actualString: String = view.text.toString()

            try {
                var actualValue = actualString.toInt()

                if (actualValue < 99)
                    actualValue += 1

                view.text = actualValue.toString()
            } catch (e: NumberFormatException) {

                e.printStackTrace()
            }
        }
    }

    /* Rest a point for long click */
    private fun restPoint(textView: TextView) {
        val actualString: String = textView.text.toString()

        try {
            var actualValue = actualString.toInt()
            if (actualValue > 0) {
                actualValue -= 1
            }

            textView.text = actualValue.toString()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
    }

    /* Reset points button */
    fun resetPoints(view: View) {
        val tbview: TextView = findViewById(R.id.teamBlueScore)
        val trview: TextView = findViewById(R.id.teamRedScore)
        tbview.text = "0"
        trview.text = "0"
    }

    /* Switch side button */
    fun switchPoints(view: View) {
        val tbview: TextView = findViewById(R.id.teamBlueScore)
        val trview: TextView = findViewById(R.id.teamRedScore)
        val temp: String = tbview.text.toString()
        tbview.text = trview.text.toString()
        trview.text = temp
    }
}