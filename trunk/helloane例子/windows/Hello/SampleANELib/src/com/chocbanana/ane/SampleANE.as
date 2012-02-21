package com.chocbanana.ane
{
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.external.ExtensionContext;
	
	public class SampleANE extends EventDispatcher {

		private var _ExtensionContext:ExtensionContext;
		
		public function SampleANE(target:IEventDispatcher=null) {
			//TODO: implement function
			super(target);
			
			_ExtensionContext = ExtensionContext.createExtensionContext("com.chocbanana.ane.SampleANE", null);
			if(_ExtensionContext == null){}

		}
		
		public function dispose():void{
			_ExtensionContext.dispose();
		}
		public function isSupported():Boolean{
			return _ExtensionContext.call("isSupported");
		}
		
		public function getTestString():String{
			return _ExtensionContext.call("getTestString") as String;
		}
		
		public function getHelloWorld():String{
			return _ExtensionContext.call("getHelloWorld") as String;
		}
	}
}