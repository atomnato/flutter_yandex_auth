package com.example.flutter_yandex_auth

import android.app.Activity
import com.example.flutter_yandex_auth.FlutterYandexAuthPlugin.Companion.okLoginManager
import com.yandex.authsdk.*
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel


class MethodCallHandler : MethodChannel.MethodCallHandler {
    companion object {
        private const val logIn = "logIn"
        var methodResult: MethodChannel.Result? = null
        var REQUEST_LOGIN_SDK = 1
    }

    private var activity: Activity? = null

    fun updateActivity(activity: Activity?) {
        this.activity = activity
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        activity!!.let {
            when (call.method) {
                logIn -> {
                    methodResult = result
                    okLoginManager = YandexAuthSdk(
                        activity!!.applicationContext,
                        YandexAuthOptions.Builder(activity!!.applicationContext)
                            .enableLogging() // Only in testing builds
                            .build())

                    val loginOptionsBuilder = YandexAuthLoginOptions.Builder()
                    val intent = okLoginManager!!.createLoginIntent(loginOptionsBuilder.build())
                    activity!!.startActivityForResult(intent,REQUEST_LOGIN_SDK)
                }

                else -> result.notImplemented()
            }
        }
    }

}