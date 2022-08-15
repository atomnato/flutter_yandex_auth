package com.example.flutter_yandex_auth

import android.app.Activity
import android.content.Intent
import com.example.flutter_yandex_auth.FlutterYandexAuthPlugin.Companion.okLoginManager
import com.example.flutter_yandex_auth.MethodCallHandler.Companion.REQUEST_LOGIN_SDK
import com.example.flutter_yandex_auth.MethodCallHandler.Companion.methodResult
import com.yandex.authsdk.YandexAuthException
import com.yandex.authsdk.YandexAuthToken
import io.flutter.plugin.common.PluginRegistry

class ActivityListener : PluginRegistry.ActivityResultListener {
    private var activity: Activity? = null

    fun updateActivity(activity: Activity?) {
        this.activity = activity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        if (requestCode == REQUEST_LOGIN_SDK) {
            try {
                val yandexAuthToken: YandexAuthToken? =
                    okLoginManager!!.extractToken(resultCode, data)
                if (yandexAuthToken != null) {
                    Thread {
                        try {
                            val jwt = okLoginManager!!.getJwt(yandexAuthToken)
                            activity!!.runOnUiThread {
                                methodResult!!.success(jwt)
                                methodResult = null
                            }
                        } catch (e: YandexAuthException) {
                            activity!!.runOnUiThread(Runnable {
                                methodResult?.error("UNAVAILABLE", "OK login error", null)
                                methodResult = null
                            })
                        }
                    }.start()
                }
                return true
            } catch (e: YandexAuthException) {
                methodResult?.error("UNAVAILABLE", "OK login error", null)
                methodResult = null
                return true
            }
        }
        return false
    }
}