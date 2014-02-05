package net.ghosrec35.overlay.client;

import java.util.HashMap;

import net.ghosrec35.overlay.CommonProxy;
import net.ghosrec35.overlay.TextureOverlayEntry;
import net.ghosrec35.overlay.client.event.TooltipDisplayEvent;
import net.ghosrec35.overlay.client.render.ItemIconRenderer;
import net.ghosrec35.overlay.event.TextureLoader;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
	public void registerClientEvents(HashMap<Item, TextureOverlayEntry> map)
	{
		ItemIconRenderer renderer = new ItemIconRenderer(map);
		
		for(Item item : map.keySet())
		{
			MinecraftForgeClient.registerItemRenderer(item.itemID, renderer);
		}
		
		MinecraftForge.EVENT_BUS.register(new TextureLoader(map));
		MinecraftForge.EVENT_BUS.register(new TooltipDisplayEvent(map));
	}
}
