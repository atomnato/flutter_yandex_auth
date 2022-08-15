import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'flutter_yandex_auth_method_channel.dart';

abstract class FlutterYandexAuthPlatform extends PlatformInterface {
  /// Constructs a FlutterYandexAuthPlatform.
  FlutterYandexAuthPlatform() : super(token: _token);

  static final Object _token = Object();

  static FlutterYandexAuthPlatform _instance = MethodChannelFlutterYandexAuth();

  /// The default instance of [FlutterYandexAuthPlatform] to use.
  ///
  /// Defaults to [MethodChannelFlutterYandexAuth].
  static FlutterYandexAuthPlatform get instance => _instance;
  
  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FlutterYandexAuthPlatform] when
  /// they register themselves.
  static set instance(FlutterYandexAuthPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
