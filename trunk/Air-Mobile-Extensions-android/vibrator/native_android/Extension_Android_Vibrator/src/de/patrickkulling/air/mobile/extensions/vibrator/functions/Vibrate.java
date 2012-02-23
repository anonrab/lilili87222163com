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
package de.patrickkulling.air.mobile.extensions.vibrator.functions;

import android.os.Vibrator;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREInvalidObjectException;
import com.adobe.fre.FREObject;
import com.adobe.fre.FRETypeMismatchException;
import com.adobe.fre.FREWrongThreadException;

import de.patrickkulling.air.mobile.extensions.vibrator.VibratorContext;

public class Vibrate implements FREFunction
{
	public FREObject call(FREContext context, FREObject[] params)
	{
		int duration = getMilliseconds(params);

		if (duration != -1)
		{
			VibratorContext vibratorContext = (VibratorContext)context;
			Vibrator vibrator = vibratorContext.vibrator;

			if(vibrator != null)
				vibrator.vibrate(duration);
		}

		return null;
	}

	protected int getMilliseconds(FREObject[] params)
	{
		int milliseconds = -1;

		try
		{
			milliseconds = params[0].getAsInt();
		} catch (IllegalStateException e)
		{
			e.printStackTrace();
		} catch (FRETypeMismatchException e)
		{
			e.printStackTrace();
		} catch (FREInvalidObjectException e)
		{
			e.printStackTrace();
		} catch (FREWrongThreadException e)
		{
			e.printStackTrace();
		}

		return milliseconds;
	}
}