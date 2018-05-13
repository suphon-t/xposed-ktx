@file:Suppress("NOTHING_TO_INLINE")

package xyz.paphonb.xposedktx.utils

import android.content.res.Resources
import android.graphics.drawable.Drawable

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

inline fun Resources.getId(name: String, packageName: String): Int {
    return getIdentifier(name, "id", packageName)
}

inline fun Resources.getDrawableId(name: String, packageName: String): Int {
    return getIdentifier(name, "drawable", packageName)
}

inline fun Resources.getDrawable(name: String, packageName: String, theme: Resources.Theme? = null): Drawable {
    return getDrawable(getDrawableId(name, packageName), theme)
}

inline fun Resources.getStringId(name: String, packageName: String): Int {
    return getIdentifier(name, "string", packageName)
}

inline fun Resources.getString(name: String, packageName: String): String? {
    return getString(getStringId(name, packageName))
}

inline fun Resources.getString(name: String, packageName: String, vararg formatArgs: Any): String? {
    return getString(getStringId(name, packageName), formatArgs)
}

inline fun Resources.getQuantityString(name: String, packageName: String, quantity: Int): String? {
    return getQuantityString(getStringId(name, packageName), quantity)
}

inline fun Resources.getArrayId(name: String, packageName: String): Int {
    return getIdentifier(name, "array", packageName)
}

inline fun Resources.getStringArray(name: String, packageName: String): Array<out String>? {
    return getStringArray(getArrayId(name, packageName))
}

inline fun Resources.getText(name: String, packageName: String): CharSequence? {
    return getText(getStringId(name, packageName))
}

inline fun Resources.getQuantityText(name: String, packageName: String, quantity: Int): CharSequence? {
    return getQuantityText(getStringId(name, packageName), quantity)
}

inline fun Resources.getTextArray(name: String, packageName: String): Array<out CharSequence>? {
    return getTextArray(getArrayId(name, packageName))
}

inline fun Resources.getIntArray(name: String, packageName: String): IntArray? {
    return getIntArray(getArrayId(name, packageName))
}

inline fun Resources.getDimensionId(name: String, packageName: String): Int {
    return getIdentifier(name, "dimen", packageName)
}

inline fun Resources.getDimension(name: String, packageName: String): Float {
    return getDimension(getDimensionId(name, packageName))
}

inline fun Resources.getDimensionPixelSize(name: String, packageName: String): Int {
    return getDimensionPixelSize(getDimensionId(name, packageName))
}

inline fun Resources.getDimensionPixelOffset(name: String, packageName: String): Int {
    return getDimensionPixelOffset(getDimensionId(name, packageName))
}

inline fun Resources.getAttributeId(name: String, packageName: String): Int {
    return getIdentifier(name, "attr", packageName)
}