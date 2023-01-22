package nl.bramdesmidt.eatdrinkrepeat

import io.hammerhead.sdk.v0.Module
import io.hammerhead.sdk.v0.ModuleFactoryI
import io.hammerhead.sdk.v0.SdkContext
import io.hammerhead.sdk.v0.datatype.SdkDataType
import nl.bramdesmidt.eatdrinkrepeat.fields.buttontest.CustomButtonType
import nl.bramdesmidt.eatdrinkrepeat.fields.timetodrink.TimeToDrinkDataType

class EatDrinkModule(context: SdkContext) : Module(context) {
    override val name: String = "Eat Drink Repeat"
    override val version: String = "0.1"

    override fun provideDataTypes(): List<SdkDataType> {
        return listOf(
            CustomButtonType(context),
            TimeToDrinkDataType(context)
        )
    }

    companion object {
        @JvmField
        val factory = object : ModuleFactoryI {
            override fun buildModule(context: SdkContext): Module {
                return EatDrinkModule(context)
            }
        }
    }
}