//
//  libSampleANE.h
//  libSampleANE
//
//  Created by  on 11/11/26.
//  Copyright 2011年 __MyCompanyName__. All rights reserved.
//

#ifndef libSampleANE_libSampleANE_h
#define libSampleANE_libSampleANE_h


#include <Adobe AIR/Adobe AIR.h>

extern "C" void initializer(void** extDataToSet, FREContextInitializer* ctxInitializerToSet, FREContextFinalizer* ctxFinalizerToSet);
extern "C" void finalizer(void** extData);

#endif
