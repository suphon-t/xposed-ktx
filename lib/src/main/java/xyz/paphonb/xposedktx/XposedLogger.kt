@file:Suppress("NOTHING_TO_INLINE")

package xyz.paphonb.xposedktx

import de.robv.android.xposed.XposedBridge

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

object XposedLogger {

    private val logFormat by lazy { "[${ModuleConfig.logTag}] %1\$s %2\$s: %3\$s" }

    fun logE(tag: String, msg: String, t: Throwable? = null) {
        XposedBridge.log(String.format(logFormat, "[ERROR]", tag, msg))
        t?.let { XposedBridge.log(it) }
    }

    fun logW(tag: String, msg: String) {
        XposedBridge.log(String.format(logFormat, "[WARNING]", tag, msg))
    }

    fun logI(tag: String, msg: String) {
        XposedBridge.log(String.format(logFormat, "[INFO]", tag, msg))
    }

    fun logD(tag: String, msg: String) {
        if (ModuleConfig.enableDebugLogging) XposedBridge.log(String.format(logFormat, "[DEBUG]", tag, msg))
    }
}

inline fun logE(tag: String, msg: String, t: Throwable? = null) {
    XposedLogger.logE(tag, msg, t)
}

inline fun logW(tag: String, msg: String) {
    XposedLogger.logW(tag, msg)
}

inline fun logI(tag: String, msg: String) {
    XposedLogger.logI(tag, msg)
}

inline fun logD(tag: String, msg: String) {
    XposedLogger.logD(tag, msg)
}