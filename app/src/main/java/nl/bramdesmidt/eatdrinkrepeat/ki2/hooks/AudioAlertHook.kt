package nl.bramdesmidt.eatdrinkrepeat.ki2.hooks

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import io.hammerhead.sdk.v0.SdkContext

// https://github.com/valterc/ki2
// https://github.com/valterc/ki2
// https://github.com/valterc/ki2
@SuppressLint("LogNotTimber")
object AudioAlertHook {
    private val ENUM_AUDIO_ALERT: Lazy<Class<out Enum<*>?>?>? = lazy {
        try {
            return@lazy Class.forName("io.hammerhead.datamodels.profiles.AudioAlert") as Class<out Enum<*>?>
        } catch (e: Exception) {
            Log.w("KI2", "Unable to get Audio Alert enum", e)
        }
        null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun triggerAudioAlert_1(context: SdkContext?, enumName: String?): Boolean {
        try {
            val audioAlertClass = ENUM_AUDIO_ALERT!!.value ?: return false
            val audioAlertEnum = java.lang.Enum.valueOf(audioAlertClass, enumName)
            val methodsAudioAlert = audioAlertClass.methods
            for (methodBroadcast in methodsAudioAlert) {
                if (methodBroadcast!!.parameterCount == 2) {
                    val parameterTypes = methodBroadcast.parameterTypes
                    if (parameterTypes[0] == Context::class.java && parameterTypes[1] == String::class.java) {
                        methodBroadcast.invoke(audioAlertEnum, context!!.baseContext, null)
                        return true
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("KI2", "Unable to trigger audio alert using method 1: $e")
        }
        return false
    }

    private fun triggerAudioAlert_2(context: SdkContext?, enumName: String?): Boolean {
        try {
            val audioAlertClass = ENUM_AUDIO_ALERT!!.value ?: return false
            val audioAlertEnum = java.lang.Enum.valueOf(audioAlertClass, enumName)
            val intent = Intent()
            intent.action = "io.hammerhead.action.AUDIO_ALERT"
            intent.putExtra("type", audioAlertEnum!!.ordinal)
            context!!.sendBroadcast(intent)
            return true
        } catch (e: Exception) {
            Log.e("KI2", "Unable to trigger audio alert using method 2: $e")
        }
        return false
    }

    private fun triggerAudioAlert_3(context: SdkContext?, enumValue: Int): Boolean {
        val intent = Intent()
        intent.action = "io.hammerhead.action.AUDIO_ALERT"
        intent.putExtra("type", enumValue)
        context!!.sendBroadcast(intent)
        return true
    }

    /**
     * Trigger a low battery audio alert.
     *
     * @param context Sdk context.
     * @return True if the alert was triggered, False otherwise.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun triggerLowBatteryAudioAlert(context: SdkContext?): Boolean {
        return triggerAudioAlert_1(context, "SENSOR_BATTERY_LOW") ||
                triggerAudioAlert_2(context, "SENSOR_BATTERY_LOW") ||
                triggerAudioAlert_3(context, 3)
    }

    /**
     * Trigger a shifting limit audio alert.
     *
     * @param context Sdk context.
     * @return True if the alert was triggered, False otherwise.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun triggerShiftingLimitAudioAlert(context: SdkContext?): Boolean {
        return triggerAudioAlert_1(context, "AUTO_LAP") ||
                triggerAudioAlert_2(context, "AUTO_LAP") ||
                triggerAudioAlert_3(context, 17)
    }

    /**
     * Trigger a synchronized shift audio alert.
     *
     * @param context Sdk context.
     * @return True if the alert was triggered, False otherwise.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    fun triggerSynchroShiftAudioAlert(context: SdkContext?): Boolean {
        return triggerAudioAlert_1(context, "WORKOUT_NEW_INTERVAL") ||
                triggerAudioAlert_2(context, "WORKOUT_NEW_INTERVAL") ||
                triggerAudioAlert_3(context, 12)
    }
}