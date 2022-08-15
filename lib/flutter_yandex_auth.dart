
import 'package:flutter/services.dart';

class FlutterYandexAuth {
  static const MethodChannel _channel = MethodChannel('flutter_yandex_auth');
  static const _methodLogIn = 'logIn';

  static Future login() async {
    final response = await _channel.invokeMethod(_methodLogIn);
    return response;
  }
}
