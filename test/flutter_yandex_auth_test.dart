import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_yandex_auth/flutter_yandex_auth.dart';
import 'package:flutter_yandex_auth/flutter_yandex_auth_platform_interface.dart';
import 'package:flutter_yandex_auth/flutter_yandex_auth_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFlutterYandexAuthPlatform 
    with MockPlatformInterfaceMixin
    implements FlutterYandexAuthPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FlutterYandexAuthPlatform initialPlatform = FlutterYandexAuthPlatform.instance;

  test('$MethodChannelFlutterYandexAuth is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFlutterYandexAuth>());
  });

  test('getPlatformVersion', () async {
    FlutterYandexAuth flutterYandexAuthPlugin = FlutterYandexAuth();
    MockFlutterYandexAuthPlatform fakePlatform = MockFlutterYandexAuthPlatform();
    FlutterYandexAuthPlatform.instance = fakePlatform;
  
    expect(await flutterYandexAuthPlugin.getPlatformVersion(), '42');
  });
}
