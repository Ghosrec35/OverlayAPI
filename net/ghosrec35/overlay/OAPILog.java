package net.ghosrec35.overlay;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

public class OAPILog 
{
	private static Logger oapiLogger = Logger.getLogger("OAPI Log");
	
	static
	{
		oapiLogger.setParent(FMLLog.getLogger());
	}
	
	public static void severe(String format, String args)
	{
		oapiLogger.severe(String.format(format, args));
	}
	
	public static void severe(String msg)
	{
		oapiLogger.severe(msg);
	}
	
	public static void fine(String format, String args)
	{
		oapiLogger.fine(String.format(format, args));
	}
	
	public static void fine(String msg)
	{
		oapiLogger.fine(msg);
	}
	
	public static void finer(String format, String args)
	{
		oapiLogger.finer(String.format(format, args));
	}
	
	public static void finer(String msg)
	{
		oapiLogger.finer(msg);
	}
	
	public static void finest(String format, String args)
	{
		oapiLogger.finest(String.format(format, args));
	}
	
	public static void finest(String msg)
	{
		oapiLogger.finest(msg);
	}
	
	public static void info(String format, String args)
	{
		oapiLogger.info(String.format(format, args));
	}
	
	public static void info(String msg)
	{
		oapiLogger.info(msg);
	}
	
	public static void warning(String format, String args)
	{
		oapiLogger.warning(String.format(format, args));
	}
	
	public static void warning(String msg)
	{
		oapiLogger.warning(msg);
	}
}
