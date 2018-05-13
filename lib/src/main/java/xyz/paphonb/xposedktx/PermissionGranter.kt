package xyz.paphonb.xposedktx

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.XC_MethodHook

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

/*
 * Copyright (C) 2015 Peter Gregus for GravityBox Project (C3C076@xda)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This class is used to grant extra permissions to packages
 */
abstract class PermissionGranter : IXposedHookLoadPackage {

    private val classPackageManagerService = "com.android.server.pm.PackageManagerService"
    private val classPackageParserPackage = "android.content.pm.PackageParser.Package"

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (lpparam.packageName != "android") return

        try {
            val pmServiceClass = XposedHelpers.findClass(classPackageManagerService, lpparam.classLoader)

            XposedHelpers.findAndHookMethod(pmServiceClass, "grantPermissionsLPw",
                    classPackageParserPackage, Boolean::class.javaPrimitiveType, String::class.java, object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    val pkgName = XposedHelpers.getObjectField(param!!.args[0], "packageName") as String
                    val extras = XposedHelpers.getObjectField(param.args[0], "mExtras")
                    val ps = XposedHelpers.callMethod(extras, "getPermissionsState")
                    val settings = XposedHelpers.getObjectField(param.thisObject, "mSettings")
                    val permissions = XposedHelpers.getObjectField(settings, "mPermissions")

                    onGrantPermissions(pkgName).forEach { grantPerm(ps, permissions, it); }
                }
            })
        } catch (t: Throwable) {
            logE("PermissionGranter", "Error while hooking grantPermissionsLPw", t)
        }
    }

    private fun grantPerm(ps: Any, permissions: Any, perm: String) {
        val p = XposedHelpers.callMethod(permissions, "get", perm)
        XposedHelpers.callMethod(ps, "grantInstallPermission", p)
    }

    /**
     * @param packageName of the package to grant permissions to
     * @return A list of extra permissions to grant
     */
    abstract fun onGrantPermissions(packageName: String): List<String>
}