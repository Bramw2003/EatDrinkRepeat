package nl.bramdesmidt.eatdrinkrepeat.fields.timetodrink

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import io.hammerhead.sdk.v0.datatype.view.SdkView
import nl.bramdesmidt.eatdrinkrepeat.R

class TimeToDrinkView(context: Context) : SdkView(context) {
    private var LastDrinkTime = 1;
    private val ReminderInterval = 15 // Interval in minutes
    private val ReminderIntervalSeconds = ReminderInterval * 60;

    override fun createView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
        val view = layoutInflater.inflate(R.layout.time_to_drink, parent, false)
        view.findViewById<RelativeLayout>(R.id.timeToDrinkLayout)
            .setOnLongClickListener { onLayoutLongClick() }
        return view
    }

    private fun onLayoutLongClick(): Boolean {
        // Ugly fix but will reset the timer on the next call to "onUpdate"
        // Requires a little less calculation then using the view and such
        LastDrinkTime += ReminderIntervalSeconds * 1000
        return true
    }

    override fun onUpdate(view: View, value: Double, formattedValue: String?) {
        val editText = view.findViewById<TextView>(R.id.text);
        val diff = value - LastDrinkTime
        val diffSeconds = ReminderIntervalSeconds - (diff / 1000)

        editText.text = String.format(
            "%02d:%02d",
            ((diffSeconds / 60) % 60).toInt(),
            (diffSeconds % 60).toInt(),
        )

        if (diffSeconds > ReminderIntervalSeconds) {
            LastDrinkTime = (value - 1).toInt()
            mDisplayToast(
                Toast.makeText(context, "Don't forget to drink!", Toast.LENGTH_LONG),
                15000
            )
        }
    }

    private fun mDisplayToast(toast: Toast, toastDuration: Int) {
        Thread {
            for (i in 1..toastDuration / 2000) {
                toast.show()
                Thread.sleep(2000)
                toast.cancel()
            }
        }.start()
    }

    override fun onInvalid(view: View) {
        if (LastDrinkTime == 1) {
            view.findViewById<TextView>(R.id.text).text = "Gaat wat mis ofzo"
        }
    }
}