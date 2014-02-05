package net.ghosrec35.overlay.lib;

public class Reference 
{
	public static final String MOD_ID = "OverlayAPI_Ghosrec35";
	public static final String MOD_NAME = "OverlayAPI";
	public static final String MOD_VERSION = "0.0.0.1";
	
	/* Need to fix dependencies so that this loads last
	 * assuring all mods have added their entries before
	 * this mod starts before actions. 
	 **/
	public static final String MOD_DEPENDENCIES = "after";
}
