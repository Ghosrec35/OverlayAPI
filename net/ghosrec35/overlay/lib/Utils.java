package net.ghosrec35.overlay.lib;

import java.awt.Color;

import cpw.mods.fml.common.FMLLog;

public class Utils
{
	/**
	 * Utility method for converting a Hexadecimal String to an RGB int array
	 * 
	 * @param String
	 *            - String to convert to hex.
	 * @return int Array containing 3 indices, Red, Green, and Blue
	 **/
	public static int[] getRGBFromHexStr(String hexStr)
	{
		Color color = Color.decode(hexStr);
		int[] rgb = new int[3];
		rgb[0] = color.getRed();
		rgb[1] = color.getGreen();
		rgb[2] = color.getBlue();
		color = null;
		return rgb;
	}

	/**
	 * Utility method for converting an rgb int array to a Hexadecimal String
	 * 
	 * @param RGB
	 *            - int array to be converting to Hexadecimal String
	 * @return String - Converted Hexadecimal String
	 **/
	public static String getHexStrFromRGB(int[] rgb)
	{
		if (rgb.length == 3) 
		{ 
			return "#" + String.format("%02X%02X%02X", rgb[0], rgb[1], rgb[2]); 
		}
		return null;
	}
}