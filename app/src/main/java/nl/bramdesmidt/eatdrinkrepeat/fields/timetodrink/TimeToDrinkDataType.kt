package nl.bramdesmidt.eatdrinkrepeat.fields.timetodrink

import io.hammerhead.sdk.v0.SdkContext
import io.hammerhead.sdk.v0.datatype.Dependency
import io.hammerhead.sdk.v0.datatype.SdkDataType
import io.hammerhead.sdk.v0.datatype.formatter.BuiltInFormatter
import io.hammerhead.sdk.v0.datatype.formatter.SdkFormatter
import io.hammerhead.sdk.v0.datatype.transformer.BuiltInTransformer
import io.hammerhead.sdk.v0.datatype.transformer.SdkTransformer
import io.hammerhead.sdk.v0.datatype.view.BuiltInView
import io.hammerhead.sdk.v0.datatype.view.SdkView
import nl.bramdesmidt.eatdrinkrepeat.fields.buttontest.CustomButtonView

class TimeToDrinkDataType(context: SdkContext) : SdkDataType(context) {
    override val typeId: String = "bds-time-to-drink"
    override val displayName: String = "Time to Drink"
    override val description: String = "Show the time to when you should drink"
    override val dependencies = listOf(Dependency.RIDE_TIME)

    override fun newView(): SdkView = TimeToDrinkView(context)
    override fun newFormatter(): SdkFormatter = BuiltInFormatter.Numeric(0)
    override fun newTransformer(): SdkTransformer = BuiltInTransformer.Identity(context)
}