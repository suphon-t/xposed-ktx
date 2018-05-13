@file:Suppress("NOTHING_TO_INLINE")

package xyz.paphonb.xposedktx.utils

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import xyz.paphonb.xposedktx.ModuleConfig

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

inline fun Context.getResourceIdentifier(name: String, type: String, packageName: String = this.packageName): Int {
    return resources.getIdentifier(name, type, packageName)
}

inline fun Context.getId(name: String, packageName: String = this.packageName): Int {
    return getResourceIdentifier(name, "id", packageName)
}

inline fun Context.getDrawableId(name: String, packageName: String = this.packageName): Int {
    return getResourceIdentifier(name, packageName)
}

inline fun Context.getDrawable(name: String, packageName: String = this.packageName): Drawable {
    return getDrawable(getDrawableId(name, packageName))
}

inline fun Context.getStringId(name: String, packageName: String = this.packageName): Int {
    return getResourceIdentifier(name, "string", packageName)
}

inline fun Context.getString(name: String, packageName: String = this.packageName): String? {
    return resources.getString(name, packageName)
}

inline fun Context.getQuantityString(name: String, quantity: Int, packageName: String = this.packageName): String? {
    return resources.getQuantityString(name, packageName, quantity)
}

inline fun Context.getArrayId(name: String, packageName: String = this.packageName): Int {
    return getResourceIdentifier(name, "array", packageName)
}

inline fun Context.getStringArray(name: String, packageName: String = this.packageName): Array<out String>? {
    return resources.getStringArray(name, packageName)
}

inline fun Context.getTextArray(name: String, packageName: String = this.packageName): Array<out CharSequence>? {
    return resources.getTextArray(name, packageName)
}

inline fun Context.getIntArray(name: String, packageName: String = this.packageName): IntArray? {
    return resources.getIntArray(name, packageName)
}

inline fun Context.getDimensionId(name: String, packageName: String = this.packageName): Int {
    return getResourceIdentifier(name, "dimen", packageName)
}

inline fun Context.getDimension(name: String, packageName: String = this.packageName): Float {
    return resources.getDimension(name, packageName)
}

inline fun Context.getDimensionPixelSize(name: String, packageName: String = this.packageName): Int {
    return resources.getDimensionPixelSize(name, packageName)
}

inline fun Context.getDimensionPixelOffset(name: String, packageName: String = this.packageName): Int {
    return resources.getDimensionPixelOffset(name, packageName)
}

inline fun Context.getAttributeId(name: String, packageName: String = this.packageName): Int {
    return getResourceIdentifier(name, "attr", packageName)
}

@ColorInt
fun Context.getColorAttr(name: String, packageName: String = this.packageName): Int {
    return getColorAttr(getAttributeId(name))
}

@ColorInt
fun Context.getColorAttr(attr: Int): Int {
    val ta = obtainStyledAttributes(intArrayOf(attr))
    @ColorInt val colorAccent = ta.getColor(0, 0)
    ta.recycle()
    return colorAccent
}

fun Context.forModule(flags: Int = Context.CONTEXT_IGNORE_SECURITY): Context? {
    return try {
        createPackageContext(ModuleConfig.packageName, flags)
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }
}

fun Context.forPackage(packageName: String, flags: Int = Context.CONTEXT_IGNORE_SECURITY): Context? {
    return try {
        createPackageContext(packageName, flags)
    } catch (e: PackageManager.NameNotFoundException) {
        null
    }
}