package net.ghosrec35.overlay;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TextureOverlayEntry 
{
	/* Bit-wise Values */
	/**
	 * Tells the Overlay API details about how to implement this Overlay.
	 **/
	public static final int NO_EXTRA_DETAIL = 1 << 0;
	public static final int USE_TOOLTIP = 1 << 1;
	public static final int REQUIRE_TOOLTIP_KEY_PRESS = 1 << 2;
	
	/**
	 * The target item this Entry shall bind these overlays to.
	 **/
	private int locationIndex = 0;
	private int iconIndex = 0;
	private String dir;
	public int details;
	private Map<Integer, OverlayInfo> overlayInfo = new HashMap<Integer, OverlayInfo>();
	private Map<Integer, Icon> overlayIcons = new HashMap<Integer, Icon>();
	
	public TextureOverlayEntry(String defaultDir, int detail)
	{
		dir = defaultDir;
		details = detail;
	}
	
	public void addOverlay(String path, String nbtKey)
	{
		addOverlay(path, nbtKey, true);
	}
	
	public void addOverlay(String path, String nbtKey, boolean useDefaultDir)
	{
		if(useDefaultDir)
			overlayInfo.put(locationIndex++, new OverlayInfo(dir + ":" + path, nbtKey));
		else
			overlayInfo.put(locationIndex++, new OverlayInfo(path, nbtKey));
	}
	
	@SideOnly(Side.CLIENT)
	public void addIcon(Icon icon)
	{
		if(icon != null)
		{
			overlayIcons.put(iconIndex++, icon);
			OAPILog.severe("Loaded Icon " + icon.getIconName() + ". Adding to overlayIcons map!");
		}
		else
			OAPILog.severe("Cannot add null Icons to the Icon Map!");
	}
	
	public OverlayInfo getInfoByLayer(int layer)
	{
		if(overlayInfo.containsKey(layer))
			return overlayInfo.get(layer);
		return null;
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIconFromLayer(int layer)
	{
		if(overlayIcons.containsKey(layer))
			return overlayIcons.get(layer);
		return null;
	}
	
	public int getNumLayers()
	{
		return locationIndex;
	}
	
	
	public boolean validate()
	{
		if(this.iconIndex == this.locationIndex)
		{
			return true;
		}
		return false;
	}
	
	public class OverlayInfo
	{
		private final String location;
		private final String nbtKey;

		private OverlayInfo(final String res, final String nbt)
		{
			location = res;
			nbtKey = nbt;
		}
		
		public String getResLocation()
		{
			return location;
		}
		
		public String getNBTKey()
		{
			return nbtKey;
		}
	}
}
