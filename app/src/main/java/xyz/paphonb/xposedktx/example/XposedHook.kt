package xyz.paphonb.xposedktx.example

import xyz.paphonb.xposedktx.BaseHook
import java.util.*

class XposedHook : BaseHook() {

    override val hooksList: List<Any> get() = arrayListOf(ClockHook)
}
