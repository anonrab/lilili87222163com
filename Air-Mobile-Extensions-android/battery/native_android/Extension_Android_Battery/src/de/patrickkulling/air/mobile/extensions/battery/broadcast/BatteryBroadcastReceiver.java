package de.patrickkulling.air.mobile.extensions.battery.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;
import de.patrickkulling.air.mobile.extensions.battery.BatteryContext;
import de.patrickkulling.air.mobile.extensions.battery.event.BatteryStatus;

public class BatteryBroadcastReceiver extends BroadcastReceiver
{
	protected BatteryContext batteryContext;

	protected int level = 0;
	protected int scale = 0;
	protected int temperature = 0;
	protected int voltage = 0;

	public BatteryBroadcastReceiver(BatteryContext context)
	{
		batteryContext = context;
	}

	@Override
	public void onReceive(Context context, Intent intent)
	{
		level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
		scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
		temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
		voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);

		if (batteryContext != null)
		{
			try
			{
				StringBuilder batteryValues = new StringBuilder(Integer.toString(level));
				batteryValues.append("&").append(Integer.toString(scale)).append("&").append(Integer.toString(temperature)).append("&").append(Integer.toString(voltage));

				batteryContext.dispatchStatusEventAsync(BatteryStatus.BATTERY_CHANGE, batteryValues.toString());
			} catch (IllegalArgumentException e)
			{
				Log.e("BatteryBroadcastReceiver", "context is not available anymore.");
			}
		}
	}
}