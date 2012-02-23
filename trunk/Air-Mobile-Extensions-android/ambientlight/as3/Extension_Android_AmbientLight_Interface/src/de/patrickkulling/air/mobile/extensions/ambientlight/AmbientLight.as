﻿/*
 * Copyright (c) 2011 Patrick Kulling
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.patrickkulling.air.mobile.extensions.ambientlight
{
	import de.patrickkulling.air.mobile.extensions.ambientlight.event.AmbientLightEvent;
	import de.patrickkulling.air.mobile.extensions.ambientlight.event.AmbientLightStatus;

	import flash.events.EventDispatcher;
	import flash.events.StatusEvent;
	import flash.events.TimerEvent;
	import flash.external.ExtensionContext;
	import flash.utils.Timer;


	[Event(name="AmbientLightEvent.UPDATE", type="de.patrickkulling.air.mobile.extensions.ambientlight.event.AmbientLightEvent")]
	public class AmbientLight extends EventDispatcher
	{
		protected static const EXTENSION_ID : String = "de.patrickkulling.air.mobile.extensions.ambientlight";

		protected static var context : ExtensionContext;
		protected static var referenceCount : int = 0;

		protected static var accuracy : Number = 0;
		protected static var lightLevel : Number = 0;
		
		protected var intervalTimer : Timer;
		protected var interval : Number = 200;

		public function AmbientLight()
		{
			if (context == null)
				initContext();

			if (context.hasEventListener(StatusEvent.STATUS) == false)
				context.addEventListener(StatusEvent.STATUS, handleAmbientLightStatus);

			createIntervalTimer();

			referenceCount++;
		}

		public static function isSupported() : Boolean
		{
			var isAmbientLightSupported : Boolean = false;

			var localContext : ExtensionContext = ExtensionContext.createExtensionContext(EXTENSION_ID, null);

			if (localContext != null)
			{
				localContext.call("initialize");
				isAmbientLightSupported = localContext.call("isSupported") as Boolean;

				localContext.dispose();
				localContext = null;
			}

			return isAmbientLightSupported;
		}

		public function setRequestedUpdateInterval(interval : Number) : void
		{
			this.interval = interval;

			disposeIntervalTimer();

			createIntervalTimer();
		}

		public function getMaximumRange() : Number
		{
			if(context == null)
				return -1;
				
			return context.call("getMaximumRange") as Number;
		}

		public function getPower() : Number
		{
			if(context == null)
				return -1;
				
			return context.call("getPower") as Number;
		}

		public function getResolution() : Number
		{
			if(context == null)
				return -1;
				
			return context.call("getResolution") as Number;
		}

		public function dispose() : void
		{
			if (context == null)
				return;

			disposeIntervalTimer();
			
			referenceCount--;

			if (referenceCount < 0)
				referenceCount = 0;

			if (referenceCount == 0)
			{
				context.removeEventListener(StatusEvent.STATUS, handleAmbientLightStatus);
				context.call("stopAmbientLight");
				context.dispose();
				context = null;
			}
		}

		protected static function initContext() : void
		{
			context = ExtensionContext.createExtensionContext(EXTENSION_ID, null);

			context.call("initialize");
			context.call("startAmbientLight");
		}

		protected function disposeIntervalTimer() : void
		{
			if (intervalTimer != null)
			{
				intervalTimer.removeEventListener(TimerEvent.TIMER, handleIntervalTimer);
				intervalTimer.stop();
				intervalTimer = null;
			}
		}

		protected function createIntervalTimer() : void
		{
			intervalTimer = new Timer(interval);
			intervalTimer.addEventListener(TimerEvent.TIMER, handleIntervalTimer);
			intervalTimer.start();
		}

		protected function handleIntervalTimer(event : TimerEvent) : void
		{
			if (context != null)
				dispatchEvent(new AmbientLightEvent(AmbientLightEvent.UPDATE, lightLevel, accuracy));
		}

		protected function handleAmbientLightStatus(event : StatusEvent) : void
		{
			switch(event.code)
			{
				case AmbientLightStatus.ACCURACY_CHANGE:
					accuracy = parseInt(event.level);

					break;
				case AmbientLightStatus.SENSOR_CHANGE:
					lightLevel = parseFloat(event.level);

					break;

				default:
			}
		}
	}
}