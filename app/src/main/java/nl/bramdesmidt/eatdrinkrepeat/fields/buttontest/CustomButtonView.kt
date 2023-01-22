/**
 * Copyright (c) 2021 Hammerhead Navigation Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.bramdesmidt.eatdrinkrepeat.fields.buttontest

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.hammerhead.sdk.v0.datatype.view.SdkView
import nl.bramdesmidt.eatdrinkrepeat.R

class CustomButtonView(context: Context) : SdkView(context) {
    override fun createView(layoutInflater: LayoutInflater, parent: ViewGroup): View {
        var view = layoutInflater.inflate(R.layout.custom_button_test, parent, false);
        view.findViewById<Button>(R.id.button)?.setOnClickListener {onButtonClick()}
        return view;
    }

    override fun onInvalid(view: View) {

    }

    private fun onButtonClick() {
        Toast.makeText(context, "Don't forget to drink!", Toast.LENGTH_LONG).show();
    }


    companion object {
        private const val MAX_SPEED = 17.0 // mps
    }
}
