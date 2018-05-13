package xyz.paphonb.xposedktx.example

import android.graphics.Color
import android.widget.TextView
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import xyz.paphonb.xposedktx.utils.hookAfter

object ClockHook : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != "com.android.systemui") return

        val classClock = XposedHelpers.findClass("com.android.systemui.statusbar.policy.Clock", lpparam.classLoader)
        classClock.hookAfter<TextView>("updateClock") {
            text = "$text :)"
            setTextColor(Color.RED)
        }
    }
}