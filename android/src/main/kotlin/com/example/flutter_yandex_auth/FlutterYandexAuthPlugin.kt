package com.example.flutter_yandex_auth

import android.annotation.SuppressLint
import com.yandex.authsdk.YandexAuthSdk

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodChannel

/** FlutterYandexAuthPlugin */
class FlutterYandexAuthPlugin: FlutterPlugin, ActivityAware {
  companion object{
    private const val channelName = "flutter_yandex_auth"
    @SuppressLint("StaticFieldLeak")
    var okLoginManager: YandexAuthSdk? = null
  }
  private var dartChannel: MethodChannel? = null
  private var methodCallHandler: MethodCallHandler? = null
  private var activityListener: ActivityListener? = null
  private var activityPluginBinding: ActivityPluginBinding? = null

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    val dartChannel = MethodChannel(flutterPluginBinding.binaryMessenger, channelName)
    val methodCallHandler = MethodCallHandler()
    val activityListener = ActivityListener()
    dartChannel.setMethodCallHandler(methodCallHandler)

    this.activityListener = activityListener
    this.dartChannel = dartChannel
    this.methodCallHandler = methodCallHandler
  }



  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    dartChannel?.setMethodCallHandler(null)

    methodCallHandler = null
    activityPluginBinding = null
    activityListener = null
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    setActivity(binding)
  }

  override fun onDetachedFromActivityForConfigChanges() {
    resetActivity()
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    setActivity(binding)
  }

  override fun onDetachedFromActivity() {
    resetActivity()
  }

  private fun setActivity(activityPluginBinding: ActivityPluginBinding) {
    methodCallHandler?.updateActivity(activityPluginBinding.activity)
    activityListener?.updateActivity(activityPluginBinding.activity)
    activityPluginBinding.addActivityResultListener(activityListener!!)
    this.activityPluginBinding = activityPluginBinding
  }

  private fun resetActivity() {
    activityPluginBinding?.removeActivityResultListener(activityListener!!)
    methodCallHandler?.updateActivity(null)
    activityListener?.updateActivity(null)
    activityPluginBinding = null
  }

}
