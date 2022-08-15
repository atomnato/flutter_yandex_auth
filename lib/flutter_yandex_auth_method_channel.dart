import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_yandex_auth_platform_interface.dart';

/// An implementation of [FlutterYandexAuthPlatform] that uses method channels.
class MethodChannelFlutterYandexAuth extends FlutterYandexAuthPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_yandex_auth');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
