package nl.bramdesmidt.eatdrinkrepeat.fields.timetodrink

import io.hammerhead.sdk.v0.datatype.formatter.SdkFormatter

class TimeToDrinkFormatter: SdkFormatter() {
    override fun formatValue(value: Double): String {
        // Ms to minutes
        val init = (value / 1000);

        val hours = kotlin.math.floor(init / 3600);
        val minutes = kotlin.math.floor((init / 60) % 60);
        val seconds = init % 60;


        return String.format("%d:%02d:02d", hours, minutes, seconds)
    }

}