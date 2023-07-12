
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNSqliteLiteSpec.h"

@interface SqliteLite : NSObject <NativeSqliteLiteSpec>
#else
#import <React/RCTBridgeModule.h>

@interface SqliteLite : NSObject <RCTBridgeModule>
#endif

@end
