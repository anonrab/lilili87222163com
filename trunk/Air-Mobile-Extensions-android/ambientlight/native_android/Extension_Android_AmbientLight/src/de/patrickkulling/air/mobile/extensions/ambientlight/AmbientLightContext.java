/*
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
package de.patrickkulling.air.mobile.extensions.ambientlight;

import java.util.HashMap;
import java.util.Map;

import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

import de.patrickkulling.air.mobile.extensions.ambientlight.functions.GetMaximumRange;
import de.patrickkulling.air.mobile.extensions.ambientlight.functions.GetPower;
import de.patrickkulling.air.mobile.extensions.ambientlight.functions.GetResolution;
import de.patrickkulling.air.mobile.extensions.ambientlight.functions.Initialize;
import de.patrickkulling.air.mobile.extensions.ambientlight.functions.IsSupported;
import de.patrickkulling.air.mobile.extensions.ambientlight.functions.StartAmbientLight;
import de.patrickkulling.air.mobile.extensions.ambientlight.functions.StopAmbientLight;
import de.patrickkulling.air.mobile.extensions.ambientlight.listener.AmbientLightListener;

public class AmbientLightContext extends FREContext
{
	protected HashMap<String, FREFunction> availableFunctions;
	
	public SensorManager sensorManager;
	public Sensor ambientLightSensor;
	public AmbientLightListener ambientLightListener;

	@Override
	public void dispose()
	{
		sensorManager = null;
		ambientLightSensor = null;
		ambientLightListener = null;

		availableFunctions = null;
	}

	@Override
	public Map<String, FREFunction> getFunctions()
	{
		availableFunctions = new HashMap<String, FREFunction>();

		availableFunctions.put("initialize", new Initialize());

		availableFunctions.put("isSupported", new IsSupported());
		availableFunctions.put("startAmbientLight", new StartAmbientLight());
		availableFunctions.put("stopAmbientLight", new StopAmbientLight());

		availableFunctions.put("getMaximumRange", new GetMaximumRange());
		availableFunctions.put("getPower", new GetPower());
		availableFunctions.put("getResolution", new GetResolution());
		
		return availableFunctions;
	}
}