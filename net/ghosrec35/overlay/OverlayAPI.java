package net.ghosrec35.overlay;

import java.util.HashMap;
import java.util.Map;

import net.ghosrec35.overlay.lib.Reference;
import net.minecraft.item.Item;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class OverlayAPI 
{
	@Instance(Reference.MOD_ID)
	private static OverlayAPI instance;
	
	@SidedProxy(clientSide = "net.ghosrec35.overlay.client.ClientProxy", serverSide = "net.ghosrec35.overlay.CommonProxy")
	public static CommonProxy proxy;
	
	private Map<Item, TextureOverlayEntry> overlayEntries = Maps.newHashMap();
	public static Item test = new Item(12533).setUnlocalizedName("test");
	@EventHandler
	private void onPreInit(FMLPreInitializationEvent event)
	{
		registerNecessaryEvents();
		
		TextureOverlayEntry entry = new TextureOverlayEntry("overlay", TextureOverlayEntry.NO_EXTRA_DETAIL);
		entry.addOverlay("test", "#24fd");
		entry.addOverlay("test1", "1251243");
		OverlayAPI.instance().addTextureOverlay(test, entry);
		
	}

	@EventHandler
	public void onPostInit(FMLPostInitializationEvent event)
	{
		OAPILog.severe("Retrieved Icon: " + overlayEntries.get(test).getIconFromLayer(0).getIconName());
		OAPILog.severe("Retrieved Icon: " + overlayEntries.get(test).getIconFromLayer(1).getIconName());
	}
	
	private void registerNecessaryEvents() 
	{
		proxy.registerClientEvents((HashMap<Item, TextureOverlayEntry>) overlayEntries);
	}
	
	public void addTextureOverlay(Item item, TextureOverlayEntry entry)
	{
		if(entry != null)
		{
			overlayEntries.put(item, entry);
		}
		else
			OAPILog.severe("Attempted to add a null overlay entry!");
	}
	
	public static OverlayAPI instance()
	{
		return instance;
	}
}
