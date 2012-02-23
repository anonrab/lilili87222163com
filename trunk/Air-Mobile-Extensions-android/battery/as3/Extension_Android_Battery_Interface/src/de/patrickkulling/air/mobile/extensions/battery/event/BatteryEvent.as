package de.patrickkulling.air.mobile.extensions.battery.event
{
	import flash.events.Event;

	public class BatteryEvent extends Event
	{
		public static const UPDATE : String = "BatteryEvent.UPDATE";
		
		protected var _level : Number;
		protected var _scale : Number;
		protected var _temperature : Number;
		protected var _voltage : Number;

		public function BatteryEvent(type : String, level : Number, scale : Number, temperature : Number, voltage : Number)
		{
			super(type, false, false);
			
			_level = level;
			_scale = scale;
			_voltage = voltage;
			_temperature = temperature;
		}

		public function get level() : Number
		{
			return _level;
		}

		public function get scale() : Number
		{
			return _scale;
		}

		public function get temperature() : Number
		{
			return _temperature;
		}

		public function get voltage() : Number
		{
			return _voltage;
		}
	}
}