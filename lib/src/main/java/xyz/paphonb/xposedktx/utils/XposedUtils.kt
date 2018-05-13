@file:Suppress("NOTHING_TO_INLINE", "UNCHECKED_CAST")

package xyz.paphonb.xposedktx.utils

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

/*
 * Copyright (C) 2018 paphonb@xda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

inline fun Any.objField(name: String) = XposedHelpers.getObjectField(this, name)

inline fun <T> Any.field(name: String) = XposedHelpers.getObjectField(this, name) as T

inline fun <T> Any.additionalField(name: String) = XposedHelpers.getAdditionalInstanceField(this, name) as T

inline fun Any.setField(name: String, value: Any?) {
    XposedHelpers.setObjectField(this, name, value)
}

inline fun Any.setAdditionalField(name: String, value: Any?) {
    XposedHelpers.setAdditionalInstanceField(this, name, value)
}

inline fun Any.setIntField(name: String, value: Int) {
    XposedHelpers.setObjectField(this, name, value)
}

inline fun <T> Any.callMethod(name: String, vararg args: Any?) = XposedHelpers.callMethod(this, name, *args) as T

inline fun <T> Class<*>.callStaticMethod(name: String, vararg args: Any?) = XposedHelpers.callStaticMethod(this, name, *args) as T

inline fun <T> Class<*>.newInstance(vararg args: Any?) = XposedHelpers.newInstance(this, *args) as T

inline fun Class<*>.hookMethod(name: String, vararg paramTypes: Class<*>, hook: XC_MethodHook) {
    XposedHelpers.findAndHookMethod(this, name, *paramTypes, hook)
}

inline fun Class<*>.hookAllMethods(name: String, hook: XC_MethodHook) {
    XposedBridge.hookAllMethods(this, name, hook)
}

inline fun <T> Class<*>.hookMethod(name: String, vararg paramTypes: Class<*>,
                                  crossinline before: T.(XC_MethodHook.MethodHookParam) -> Unit = { },
                                  crossinline after: T.(XC_MethodHook.MethodHookParam) -> Unit = { }) {
    XposedHelpers.findAndHookMethod(this, name, *paramTypes, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            before(param.thisObject as T, param)
        }

        override fun afterHookedMethod(param: MethodHookParam) {
            after(param.thisObject as T, param)
        }
    })
}

inline fun <T> Class<*>.hookAllMethods(name: String,
                                  crossinline before: T.(XC_MethodHook.MethodHookParam) -> Unit = { },
                                  crossinline after: T.(XC_MethodHook.MethodHookParam) -> Unit = { }) {
    XposedBridge.hookAllMethods(this, name, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            before(param.thisObject as T, param)
        }

        override fun afterHookedMethod(param: MethodHookParam) {
            after(param.thisObject as T, param)
        }
    })
}

inline fun <T> Class<*>.hookBefore(name: String, vararg paramTypes: Class<*>,
                                  crossinline before: T.(XC_MethodHook.MethodHookParam) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, *paramTypes, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            before(param.thisObject as T, param)
        }
    })
}

inline fun <T> Class<*>.hookAllBefore(name: String,
                                  crossinline before: T.(XC_MethodHook.MethodHookParam) -> Unit) {
    XposedBridge.hookAllMethods(this, name, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            before(param.thisObject as T, param)
        }
    })
}

inline fun <T> Class<*>.hookBefore(name: String,
                                  crossinline before: T.(XC_MethodHook.MethodHookParam) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            before(param.thisObject as T, param)
        }
    })
}

inline fun <T, reified P1> Class<*>.hookBefore(name: String,
                                  crossinline before: T.(XC_MethodHook.MethodHookParam, P1) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            before(param.thisObject as T, param, param.args[0] as P1)
        }
    })
}

inline fun <T, reified P1, reified P2> Class<*>.hookBefore(name: String,
                                  crossinline before: T.(XC_MethodHook.MethodHookParam, P1, P2) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            before(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2)
        }
    })
}

inline fun <T, reified P1, reified P2, reified P3> Class<*>.hookBefore(name: String,
                                  crossinline before: T.(XC_MethodHook.MethodHookParam, P1, P2, P3) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, P3::class.java, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            before(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2,
                    param.args[2] as P3)
        }
    })
}

inline fun <T, reified P1, reified P2, reified P3, reified P4> Class<*>.hookBefore(name: String,
                                  crossinline before: T.(XC_MethodHook.MethodHookParam, P1, P2, P3, P4) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, P3::class.java, P4::class.java, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            before(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2,
                    param.args[2] as P3, param.args[3] as P4)
        }
    })
}

inline fun <T, reified P1, reified P2, reified P3, reified P4, reified P5> Class<*>.hookBefore(name: String,
                                  crossinline before: T.(XC_MethodHook.MethodHookParam, P1, P2, P3, P4, P5) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, P3::class.java, P4::class.java, P5::class.java, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            before(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2,
                    param.args[2] as P3, param.args[3] as P4, param.args[4] as P5)
        }
    })
}

inline fun <T> Class<*>.hookAfter(name: String, vararg paramTypes: Class<*>,
                                  crossinline after: T.(XC_MethodHook.MethodHookParam) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, *paramTypes, object : XC_MethodHook() {
        override fun afterHookedMethod(param: MethodHookParam) {
            after(param.thisObject as T, param)
        }
    })
}

inline fun <T> Class<*>.hookAfter(name: String,
                                  crossinline after: T.(XC_MethodHook.MethodHookParam) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, object : XC_MethodHook() {
        override fun afterHookedMethod(param: MethodHookParam) {
            after(param.thisObject as T, param)
        }
    })
}

inline fun <T, reified P1> Class<*>.hookAfter(name: String,
                                  crossinline after: T.(XC_MethodHook.MethodHookParam, P1) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, object : XC_MethodHook() {
        override fun afterHookedMethod(param: MethodHookParam) {
            after(param.thisObject as T, param, param.args[0] as P1)
        }
    })
}

inline fun <T, reified P1, reified P2> Class<*>.hookAfter(name: String,
                                  crossinline after: T.(XC_MethodHook.MethodHookParam, P1, P2) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, object : XC_MethodHook() {
        override fun afterHookedMethod(param: MethodHookParam) {
            after(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2)
        }
    })
}

inline fun <T, reified P1, reified P2, reified P3> Class<*>.hookAfter(name: String,
                                  crossinline after: T.(XC_MethodHook.MethodHookParam, P1, P2, P3) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, P3::class.java, object : XC_MethodHook() {
        override fun afterHookedMethod(param: MethodHookParam) {
            after(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2,
                    param.args[2] as P3)
        }
    })
}

inline fun <T, reified P1, reified P2, reified P3, reified P4> Class<*>.hookAfter(name: String,
                                  crossinline after: T.(XC_MethodHook.MethodHookParam, P1, P2, P3, P4) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, P3::class.java, P4::class.java, object : XC_MethodHook() {
        override fun afterHookedMethod(param: MethodHookParam) {
            after(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2,
                    param.args[2] as P3, param.args[3] as P4)
        }
    })
}

inline fun <T, reified P1, reified P2, reified P3, reified P4, reified P5> Class<*>.hookAfter(name: String,
                                  crossinline after: T.(XC_MethodHook.MethodHookParam, P1, P2, P3, P4, P5) -> Unit) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, P3::class.java, P4::class.java, P5::class.java, object : XC_MethodHook() {
        override fun afterHookedMethod(param: MethodHookParam) {
            after(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2,
                    param.args[2] as P3, param.args[3] as P4, param.args[4] as P5)
        }
    })
}

inline fun <T, R> Class<*>.replace(name: String, vararg paramTypes: Class<*>,
                                  crossinline replacement: T.(XC_MethodHook.MethodHookParam) -> R) {
    XposedHelpers.findAndHookMethod(this, name, *paramTypes, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            param.result = replacement(param.thisObject as T, param)
        }
    })
}

inline fun <T, R> Class<*>.replace(name: String,
                                  crossinline replacement: T.(XC_MethodHook.MethodHookParam) -> R) {
    XposedHelpers.findAndHookMethod(this, name, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            param.result = replacement(param.thisObject as T, param)
        }
    })
}

inline fun <T, reified P1, R> Class<*>.replace(name: String,
                                  crossinline replacement: T.(XC_MethodHook.MethodHookParam, P1) -> R) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            param.result = replacement(param.thisObject as T, param, param.args[0] as P1)
        }
    })
}

inline fun <T, reified P1, reified P2, R> Class<*>.replace(name: String,
                                  crossinline replacement: T.(XC_MethodHook.MethodHookParam, P1, P2) -> R) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            param.result = replacement(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2)
        }
    })
}

inline fun <T, reified P1, reified P2, reified P3, R> Class<*>.replace(name: String,
                                  crossinline replacement: T.(XC_MethodHook.MethodHookParam, P1, P2, P3) -> R) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, P3::class.java, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            param.result = replacement(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2,
                    param.args[2] as P3)
        }
    })
}

inline fun <T, reified P1, reified P2, reified P3, reified P4, R> Class<*>.replace(name: String,
                                  crossinline replacement: T.(XC_MethodHook.MethodHookParam, P1, P2, P3, P4) -> R) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, P3::class.java, P4::class.java, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            param.result = replacement(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2,
                    param.args[2] as P3, param.args[3] as P4)
        }
    })
}

inline fun <T, reified P1, reified P2, reified P3, reified P4, reified P5, R> Class<*>.replace(name: String,
                                  crossinline replacement: T.(XC_MethodHook.MethodHookParam, P1, P2, P3, P4, P5) -> R) {
    XposedHelpers.findAndHookMethod(this, name, P1::class.java, P2::class.java, P3::class.java, P4::class.java, P5::class.java, object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            param.result = replacement(param.thisObject as T, param, param.args[0] as P1, param.args[1] as P2,
                    param.args[2] as P3, param.args[3] as P4, param.args[4] as P5)
        }
    })
}
