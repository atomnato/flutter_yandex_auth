#import "FlutterYandexAuthPlugin.h"
#if __has_include(<flutter_yandex_auth/flutter_yandex_auth-Swift.h>)
#import <flutter_yandex_auth/flutter_yandex_auth-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_yandex_auth-Swift.h"
#endif

@implementation FlutterYandexAuthPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterYandexAuthPlugin registerWithRegistrar:registrar];
}
@end
