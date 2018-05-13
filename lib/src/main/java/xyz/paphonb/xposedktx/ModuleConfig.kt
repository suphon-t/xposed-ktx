package xyz.paphonb.xposedktx

import android.annotation.SuppressLint
import android.content.Context
import xyz.paphonb.xposedktx.utils.forModule

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

@SuppressLint("StaticFieldLeak")
object ModuleConfig {

    var enableDebugLogging = true
    var logTag = "xposed-ktx"
    var packageName = BuildConfig.APPLICATION_ID
    var context: Context? = null

    fun getContext(context: Context): Context {
        if (this.context == null) {
            this.context = context.forModule()
        }
        return this.context!!
    }
}