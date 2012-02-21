// dllmain.cpp : DLL アプリケーションのエントリ ポイントを定義します。
#include "stdafx.h"
#include "FlashRuntimeExtensions.h"
#include <stdlib.h>
#include <string.h> 


extern "C" __declspec(dllexport) void initializer(void** extData, FREContextInitializer* ctxInitializer, FREContextFinalizer* ctxFinalizer);
extern "C" __declspec(dllexport) void finalizer(void** extData);


FREObject isSupported(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[])
{
   FREObject result;

   uint32_t isSupportedInThisOS = 1;
   FRENewObjectFromBool(isSupportedInThisOS, &result);

   return result;
}

FREObject getTestString(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[])
{
   FREObject result;

   const char *str = "This is a test string from ANE!";

   FRENewObjectFromUTF8(strlen(str)+1, (const uint8_t *)str, &result);

   return result;
}

FREObject getHelloWorld(FREContext ctx, void* funcData, uint32_t argc, FREObject argv[])
{
   FREObject result;

   const char *str = "Hello ANE World!! This is your DLL talking.";
   FRENewObjectFromUTF8(strlen(str)+1, (const uint8_t *)str, &result);
   return result;
}

void contextFinalizer(FREContext ctx)
{
	return;
}

void contextInitializer(void* extData, const uint8_t* ctxType, FREContext ctx, uint32_t* numFunctions, const FRENamedFunction** functions)
{

	*numFunctions = 3;
	FRENamedFunction*  func= (FRENamedFunction*)malloc(sizeof(FRENamedFunction) * (*numFunctions));
	//FRENamedFunction* func = (FRENamedFunction*)malloc(sizeof(FRENamedFunction)*1);

	func[0].name = (const uint8_t*) "getTestString";
	func[0].functionData = NULL;
	func[0].function = &getTestString;

	func[1].name = (const uint8_t*) "isSupported";
	func[1].functionData = NULL;
	func[1].function = &isSupported;

	func[2].name = (const uint8_t*) "getHelloWorld";
	func[2].functionData = NULL;
	func[2].function = &getHelloWorld;

	*functions = func;
}

void initializer(void** extData, FREContextInitializer* ctxInitializer, FREContextFinalizer* ctxFinalizer)
{
	*ctxInitializer = &contextInitializer;
	*ctxFinalizer = &contextFinalizer;
}

void finalizer(void** extData)
{

}







BOOL APIENTRY DllMain( HMODULE hModule,
                       DWORD  ul_reason_for_call,
                       LPVOID lpReserved
					 )
{
	switch (ul_reason_for_call)
	{
	case DLL_PROCESS_ATTACH:
	case DLL_THREAD_ATTACH:
	case DLL_THREAD_DETACH:
	case DLL_PROCESS_DETACH:
		break;
	}
	return TRUE;
}

