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
        val t1Score: TextView = findViewById(R.id.team1Score)
        val t2Score: TextView = findViewById(R.id.team2Score)
        t1Score.tag = "scores"
        t2Score.tag = "scores"

        // Sets
        val t1Set: TextView = findViewById(R.id.team1Set)
        val t2Set: TextView = findViewById(R.id.team2Set)
        t1Set.tag = "sets"
        t2Set.tag = "sets"

        // Ideal screen size for score
        val density = resources.displayMetrics.density
        val scoreHeightPixels = resources.displayMetrics.heightPixels * 0.75f
        val scoreWidthPixels = resources.displayMetrics.widthPixels * 0.4f
        val scoreSizeInSp = scoreHeightPixels / density
        val setSizeInSp = scoreSizeInSp / 2

        for (tview in listOf(t2Score, t1Score)) {
            tview.setTextSize(TypedValue.COMPLEX_UNIT_SP, scoreSizeInSp)
            tview.width = scoreWidthPixels.toInt()

        }

        for (tview in listOf(t1Set, t2Set)) {
            tview.setTextSize(TypedValue.COMPLEX_UNIT_SP, setSizeInSp)
        }


        for (tview in listOf(t2Score, t1Score, t1Set, t2Set)) {
            setLongClickListener(tview)
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

                if (view.tag == "scores" && actualValue < 99) {
                    actualValue += 1
                } else if (view.tag == "sets" && actualValue < 7) {
                    actualValue += 1
                }

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
        val tbview: TextView = findViewById(R.id.team2Score)
        val trview: TextView = findViewById(R.id.team1Score)
        val t1SetView: TextView = findViewById(R.id.team1Set)
        val t2SetView: TextView = findViewById(R.id.team2Set)
        for (textview in listOf(tbview, trview, t1SetView, t2SetView)) {
            textview.text = "0"
        }
    }

    /* Switch side button */
    fun switchPoints(view: View) {
        val t1Score: TextView = findViewById(R.id.team1Score)
        val t2Score: TextView = findViewById(R.id.team2Score)
        val t1Set: TextView = findViewById(R.id.team1Set)
        val t2Set: TextView = findViewById(R.id.team2Set)
        val t1Color: Int = t1Score.currentTextColor
        val t2Color: Int = t2Score.currentTextColor

        val tempScore: String = t1Score.text.toString()
        t1Score.text = t2Score.text.toString()
        t2Score.text = tempScore

        val tempSet: String = t1Set.text.toString()
        t1Set.text = t2Set.text.toString()
        t2Set.text = tempSet

        val tempColor: Int = t1Color
        t1Score.setTextColor(t2Color)
        t1Set.setTextColor(t2Color)
        t2Score.setTextColor(tempColor)
        t2Set.setTextColor(tempColor)

    }
}