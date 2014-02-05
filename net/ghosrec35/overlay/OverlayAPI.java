package net.ghosrec35.overlay;

import java.util.HashMap;
import java.util.Map;

import net.ghosrec35.overlay.client.render.ItemIconRenderer;
import net.ghosrec35.overlay.lib.Reference;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = Reference.MOD_DEPENDENCIES)
public class OverlayAPI 
{
	@Instance(Reference.MOD_ID)
	private static OverlayAPI instance;
	
	@SidedProxy(clientSide = "net.ghosrec35.overlay.client.ClientProxy", serverSide = "net.ghosrec35.overlay.CommonProxy")
	public static CommonProxy proxy;
	
	private Map<Item, TextureOverlayEntry> overlayEntries = Maps.newHashMap();
	
	@EventHandler
	private void onPreInit(FMLPreInitializationEvent event)
	{
		registerNecessaryEvents();
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
